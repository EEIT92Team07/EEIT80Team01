package item.category.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import item.category.model.ItemCategoryBean;

public class ItemCategoryDAOjdbc {
private DataSource ds = null;
	
	//DriverManager用
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=EEIT80TEAM01";
	private static final String USER = "sa";
	private static final String PASSWORD = "sa123456";
	
	//DataSource用
//	public ItemsDAOjdbc(){
//		Context ctx;
//		try {
//			ctx = new InitialContext();
//			ds = (DataSource)ctx.lookup(GlobalService.JNDI_DB_NAME);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	private static final String SELECT_BY_ITEMCATEGORY = "SELECT * FROM ITEMCLASS WHERE ITEM_CATEGORY = ?";

	public ItemCategoryBean selectItemCategory(int itemCategory){
		ItemCategoryBean result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_ITEMCATEGORY);
			stmt.setInt(1, itemCategory);
			rset = stmt.executeQuery();
			if(rset.next()){
				result = new ItemCategoryBean();
				result.setItemCategory(rset.getInt("ITEM_CATEGORY"));
				result.setCategoryName(rset.getString("CATEGORY_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rset!=null){
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	private static final String SELECT_ALL = "SELECT * FROM ITEMCLASS ";

	public List<ItemCategoryBean> selectAll(){
		List<ItemCategoryBean> result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			result = new ArrayList<ItemCategoryBean>();
			while(rset.next()){
				ItemCategoryBean item = new ItemCategoryBean();
				item.setItemCategory(rset.getInt("ITEM_CATEGORY"));
				item.setCategoryName(rset.getString("CATEGORY_NAME"));
				result.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rset!=null){
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	private static final String INSERT = "INSERT INTO ITEMCLASS(ITEM_CATEGORY, CATEGORY_NAME) VALUES(?,?)";

	public ItemCategoryBean insert(ItemCategoryBean bean){
		ItemCategoryBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			conn = ds.getConnection();
			stmt = conn.prepareStatement(INSERT);
			if(bean!=null){
				stmt.setInt(1, bean.getItemCategory());
				stmt.setString(2, bean.getCategoryName());
				int i = stmt.executeUpdate();
				if(i == 1){
					result = bean;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static final String UPDATE ="UPDATE ITEMCLASS SET CATEGORY_NAME=? WHERE ITEM_CATEGORY=?";

	public ItemCategoryBean update(String categoryName, int itemCategory){
		ItemCategoryBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			conn = ds.getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, categoryName);
			stmt.setInt(2, itemCategory);
			int i = stmt.executeUpdate();
			if(i == 1){
				result = this.selectItemCategory(itemCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static final String DELETE = "DELETE FROM ITEMCLASS WHERE ITEM_CATEGORY=?";

	public boolean delete(int itemCategory){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			conn = ds.getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, itemCategory);
			int i = stmt.executeUpdate();
			if(i == 1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		ItemCategoryDAOjdbc dao = new ItemCategoryDAOjdbc();
		
		//新增
//		ItemClassBean bean = new ItemClassBean();
//		bean.setItemCategory(3);
//		bean.setCategoryName("生活用品");
//		dao.insert(bean);
//		System.out.println("執行新增");
		//修改
//		ItemClassBean bean2  = new ItemClassBean();
//		bean2.setItemCategory(2);
//		bean2.setCategoryName("日常用品");
//		dao.update(bean2.getCategoryName(), bean2.getItemCategory());
//		System.out.println("執行修改");
		//刪除
//		dao.delete(3);
//		System.out.println("執行刪除");
		//查一筆
//		ItemClassBean bean3 = dao.selectItemCategory(1);
//		System.out.println(bean3.getItemCategory()+","+bean3.getCategoryName());
//		System.out.println("查詢一筆");
		//查全部
//		List<ItemClassBean> bean4 = dao.selectAll();
//		for(ItemClassBean list : bean4){
//			System.out.println(list.getItemCategory()+","+list.getCategoryName());
//		}
//		System.out.println("查詢全部");
	}

}
