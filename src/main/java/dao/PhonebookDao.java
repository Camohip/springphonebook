package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import vo.PhonebookVO;

@Repository
public class PhonebookDao implements PhonebookDaoInter{
	
	Connection conn;
	PreparedStatement pstmt;

	public PhonebookDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "shin", "9971");
		} catch (Exception e) {

		}
	}
	@Test
	public void test() {
		System.out.println(new PhonebookDao().conn);
		int result=insert(new PhonebookVO("shin", "010-1111-8589", "과정평가형시험"));
		System.out.println(result);
		//findOne();
		//find();
		//update();
		//delete();
	}
	
	@Override
	public int insert(PhonebookVO pb) {
		try {
			//String sql = "insert into phonebook values(?,?,?,?)";
			String sql = "insert into phonebook values(phonebook_idx_seq.nextval,?,?,?)";
			System.out.println(pb.toString());
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, pb.getIdx());
			pstmt.setString(1, pb.getName());
			pstmt.setString(2, pb.getHp());
			pstmt.setString(3, pb.getMemo());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println("DataBase insert 오류");
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public List<PhonebookVO> getList() {
		try {
			String sql = "select * from phonebook order by idx desc";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<PhonebookVO> list = new ArrayList();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String memo = rs.getString("memo");
				PhonebookVO imsi = new PhonebookVO(idx, name, hp, memo);
				list.add(imsi);
			}
			return list;
		} catch (Exception e) {
			System.out.println("DataBase getList 오류");
			return null;
		}
	}

	@Override
	public PhonebookVO findOne(int _idx) {
		try {
			String sql = "select * from phonebook where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, _idx);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int idx = rs.getInt("idx");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String memo = rs.getString("memo");
				PhonebookVO imsi = new PhonebookVO(idx, name, hp, memo);
				return imsi;
			}
			return null;
		} catch (Exception e) {
			System.out.println("DataBase findOne 오류");
			return null;
		}
	}

	@Override
	public List<PhonebookVO> find(String _search) {
		try {
			//String sql = "select * from phonebook where name like '%"+_search+"%'";
			String sql = "select * from phonebook where (name like '%"+_search+"%' OR hp like '%"+_search+"%' OR memo like '%"+_search+"%')";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<PhonebookVO> list = new ArrayList();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String memo = rs.getString("memo");
				PhonebookVO imsi = new PhonebookVO(idx, name, hp, memo);
				list.add(imsi);
			}
			return list;
		} catch (Exception e) {
			System.out.println("DataBase find 오류");
			return null;
		}
	}

	@Override
	public int update(PhonebookVO pb) {
		try {
				String sql = "update phonebook set name=?,hp=? ,memo=? where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pb.getName());
				pstmt.setString(2, pb.getHp());
				pstmt.setString(3, pb.getMemo());
				pstmt.setInt(4, pb.getIdx());
				int result = pstmt.executeUpdate();
				return result;
		} catch (Exception e) {
			System.out.println("DataBase Update 오류");
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int idx) {
		try {
				String sql = "delete from phonebook where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				int result = pstmt.executeUpdate();
				return result;
		} catch (Exception e) {
			System.out.println("DataBase Delete 오류");
			return 0;
		}
	}
}
