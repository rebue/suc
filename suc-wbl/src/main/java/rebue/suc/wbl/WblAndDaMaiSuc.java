package rebue.suc.wbl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import rebue.suc.mo.SucUserMo;
import rebue.wheel.DbUtils;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.idworker.IdWorker3;

public class WblAndDaMaiSuc {

	@Test
	public void main() throws SQLException, IOException {
		Connection mySqlConn = (Connection) DbUtils.getMySqlConn();
		String sql = "select * from suc.wbl_suc_user";
		PreparedStatement statement = (PreparedStatement) mySqlConn.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			// ID
			Long id = rs.getLong(1);
			// 微信ID（unionid）
			String unionid = rs.getString(19);

			System.out.println(unionid);
			if (!StringUtil.isEmpty(unionid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("wxId", unionid);
				String users = OkhttpUtils.get("http://127.0.0.1:9100/suc/user/getone", map);
				System.out.println(users);
				if (StringUtil.isEmpty(users)) {
					add(id);
				} else {
					System.out.println("该用户已存在");
					// 查询账号余额信息
					String getAccountByIdSql = "SELECT * FROM afc.wbl_afc_account where ID=" + id;
					PreparedStatement getAccountStatement = (PreparedStatement) mySqlConn
							.prepareStatement(getAccountByIdSql);
					ResultSet getAccountResult = getAccountStatement.executeQuery();
					while (getAccountResult.next()) {
						// 余额
						BigDecimal balance = getAccountResult.getBigDecimal(2);
						// 提现中总额
						BigDecimal withdrawing = getAccountResult.getBigDecimal(5);
						// 返现金余额
						BigDecimal cashback = getAccountResult.getBigDecimal(6);
						// 进货保证金
						BigDecimal deposit = getAccountResult.getBigDecimal(8);
						ObjectMapper mapper = new ObjectMapper();
						SucUserMo sucUserMo = mapper.readValue(users, SucUserMo.class);
						Map<String, Object> m = new HashMap<>();
						m.put("accountId", sucUserMo.getId());
						m.put("tradeAmount", balance);
					}
				}
			} else {
				add(id);
			}
		}
	}

	public void add(Long id) throws SQLException, IOException {
		IdWorker3 idWorker3 = new IdWorker3();
		Connection mySqlConn = (Connection) DbUtils.getMySqlConn();
		String getUserBIdSql = "select * from wbl_suc_user where ID = " + id;
		PreparedStatement statement = (PreparedStatement) mySqlConn.prepareStatement(getUserBIdSql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			// 登录名称
			String loginName = rs.getString(2);
			// 登录密码
			String loginPswd = rs.getString(3);
			// 支付密码
			String payPswd = rs.getString(4);
			// 加盐值
			String salt = rs.getString(5);
			// 昵称
			String nickName = rs.getString(6);
			// 头像
			String face = rs.getString(7);
			// 实名
			String realName = rs.getString(8);
			// 是否已验证实名
			Boolean isVerifiedRealname = rs.getBoolean(9);
			// 身份证号
			String idcard = rs.getString(10);
			// 是否已验证身份证号
			Boolean isVerifiedIdcard = rs.getBoolean(11);
			// 邮箱
			String email = rs.getString(12);
			// 是否已验证邮箱
			Boolean isVerifiedEmail = rs.getBoolean(13);
			// 手机
			String mobile = rs.getString(14);
			// 是否已验证手机
			Boolean isVerifiedMobile = rs.getBoolean(15);
			// QQId
			String qqId = rs.getString(16);
			// QQ昵称
			String qqNickName = rs.getString(17);
			// QQ头像
			String qqFace = rs.getString(18);
			// 微信ID（unionid）
			String unionid = rs.getString(19);
			// 微信昵称
			String wxNickName = rs.getString(20);
			// 微信头像
			String wxFace = rs.getString(21);
			// 是否锁定
			Boolean isLock = rs.getBoolean(22);
			// 修改时间戳
			Long modifiedTimestamp = rs.getLong(23);

			String userExist = OkhttpUtils.get("http://127.0.0.1:/9100/user/exist?id=" + id);
			Long newId = id;
			if (userExist.equals("true")) {
				newId = idWorker3.getId();
				saveArticle("newUserId: " + newId + " === oldUserId: " + id + "\r\n");
			}
			String addUserSql = "INSERT INTO suc.suc_user\n"
					+ "(ID, ORG_ID, LOGIN_NAME, LOGIN_PSWD, PAY_PSWD, SALT, NICKNAME, FACE, "
					+ "REALNAME, IS_VERIFIED_REALNAME, IDCARD, IS_VERIFIED_IDCARD, EMAIL, "
					+ "IS_VERIFIED_EMAIL, MOBILE, IS_VERIFIED_MOBILE, QQ_ID, QQ_OPENID, "
					+ "QQ_NICKNAME, QQ_FACE, WX_ID, WX_OPENID, WX_NICKNAME, WX_FACE, IS_TESTER, "
					+ "IS_LOCK, PROMOTER_ID, MODIFIED_TIMESTAMP)\n" + "VALUES\n" + "(" + newId + ", 'wbl', '"
					+ loginName + "', '" + loginPswd + "', '" + payPswd + "', '" + salt + "', '" + nickName + "', '"
					+ face + "', '" + realName + "', " + isVerifiedRealname + ", '" + idcard + "', '" + isVerifiedIdcard
					+ "', '" + email + "', " + isVerifiedEmail + ", '" + mobile + "', " + isVerifiedMobile + ", '"
					+ qqId + "', null, '" + qqNickName + "', '" + qqFace + "', '" + unionid + "', null, '" + wxNickName
					+ "', '" + wxFace + "', false, " + isLock + ", null, " + modifiedTimestamp + "  )";
			PreparedStatement addUserStatement = (PreparedStatement) mySqlConn.prepareStatement(addUserSql);
			addUserStatement.executeUpdate();

			String getRegByIdSql = "SELECT * FROM suc.wbl_suc_reg where ID=" + id;
			PreparedStatement getRegStatement = (PreparedStatement) mySqlConn.prepareStatement(getRegByIdSql);
			ResultSet getRegResult = getRegStatement.executeQuery();
			while (getRegResult.next()) {
				// 注册时间
				String regTime = getRegResult.getString(2);
				// 注册类型
				Byte regType = getRegResult.getByte(3);
				// 推广者id
				Long promoterId = getRegResult.getLong(4);
				// 应用ID
				Byte appId = getRegResult.getByte(5);
				// 浏览器类型
				String userAgent = getRegResult.getString(6);
				// mac
				String mac = getRegResult.getString(7);
				// ip
				String ip = getRegResult.getString(8);

				String addRegSql = "INSERT INTO suc.suc_reg"
						+ " (ID, REG_TIME, REG_TYPE, PROMOTER_ID, APP_ID, USER_AGENT, MAC, IP, SYS_ID)" + " VALUES ("
						+ newId + ", '" + regTime + "', " + regType + ", " + promoterId + ", " + appId + ", '"
						+ userAgent + "', '" + mac + "', '" + ip + "', 'wbl')";
				PreparedStatement addRegStatement = (PreparedStatement) mySqlConn.prepareStatement(addRegSql);
				addRegStatement.executeUpdate();
			}

			String getAccountByIdSql = "SELECT * FROM afc.wbl_afc_account where ID=" + id;
			PreparedStatement getAccountStatement = (PreparedStatement) mySqlConn.prepareStatement(getAccountByIdSql);
			ResultSet getAccountResult = getAccountStatement.executeQuery();
			while (getAccountResult.next()) {
				// 余额
				BigDecimal balance = getAccountResult.getBigDecimal(2);
				// 最后结算余额
				BigDecimal settleBalance = getAccountResult.getBigDecimal(3);
				// 最后结算时间
				String settleTime = getAccountResult.getString(4);
				// 提现中总额
				BigDecimal withdrawing = getAccountResult.getBigDecimal(5);
				// 返现金余额
				BigDecimal cashback = getAccountResult.getBigDecimal(6);
				// 返现中的总额
				BigDecimal cashbacking = getAccountResult.getBigDecimal(7);
				// 进货保证金
				BigDecimal deposit = getAccountResult.getBigDecimal(8);
				// 已占用的保证金
				BigDecimal depositUsed = getAccountResult.getBigDecimal(9);
				// 修改时间戳
				Long accountModifiedTimestamp = getAccountResult.getLong(10);

				String addAccountSql = "INSERT INTO afc.afc_account\n"
						+ "(ID, BALANCE, SETTLE_BALANCE, SETTLE_TIME, COMMISSION_TOTAL, COMMISSIONING, WITHDRAWING,\n"
						+ "CASHBACK, CASHBACKING, DEPOSIT, DEPOSIT_USED, ACCOUNT_TYPE, MODIFIED_TIMESTAMP)\n"
						+ "VALUES (" + id + ", " + balance + ", " + settleBalance + ", '" + settleTime + "', 0, 0, "
						+ withdrawing + ", " + cashback + ", " + cashbacking + ", " + deposit + ", " + depositUsed
						+ ", 1, " + accountModifiedTimestamp + ")";
				PreparedStatement addAccountStatement = (PreparedStatement) mySqlConn.prepareStatement(addAccountSql);
				addAccountStatement.executeUpdate();
			}
		}
	}

	/**
	 * 保存文章到本地
	 * 
	 * @param titile
	 * @param content
	 * @param blogName
	 */
	public static void saveArticle(String content) {
		String lujing = "d:\\已改变的用户id.text";// 保存到本地的路径和文件名
		File file = new File(lujing);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
