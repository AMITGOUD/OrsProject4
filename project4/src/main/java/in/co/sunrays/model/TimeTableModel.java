package in.co.sunrays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.bean.CourceBean;
import in.co.sunrays.bean.SubjectBean;
import in.co.sunrays.bean.TimeTableBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DatabaseException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.util.JDBCDataSource;

public class TimeTableModel {

	private static Logger log = Logger.getLogger(TimeTableModel.class);

	/**
	 * Find next PK of College
	 *
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_TIMETABLE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a College
	 *
	 * @param bean
	 * @throws DatabaseException
	 *
	 */
	public long add(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		//code for getting courcename
        CourceBean cbn=new CourceBean();
        CourceModel cmodel=new CourceModel();
        cbn=cmodel.findByPK(bean.getCourceId());
        //here we set courcename in timetableBean
        bean.setCourceName(cbn.getCourcename());
        
        SubjectBean sbn=new SubjectBean();
        SubjectModel smodel=new SubjectModel();
       sbn =smodel.findByPK(bean.getSubjectId());
       
       System.out.println(sbn.getSubjectName());
       
       bean.setSubjectName(sbn.getSubjectName());
		TimeTableBean duplicatesubject = findByName(bean.getSubjectName());

		if (duplicatesubject != null) {
			throw new DuplicateRecordException("Subject Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setLong(2, bean.getCourceId());
			pstmt.setString(3, bean.getCourceName());
			pstmt.setString(4, bean.getSubjectName());
			pstmt.setString(5, bean.getExamDate());
			pstmt.setString(6, bean.getExamTime());
			pstmt.setString(7, bean.getSemester());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add TIMETABLE");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a College
	 *
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete(TimeTableBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in DELETE TIMETABLE");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete END");
	}

	/**
	 * Find User by College
	 *
	 * @param login : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public TimeTableBean findByName(String name) throws ApplicationException {
		log.debug("Model findBySUBJECTCODE Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE SUBJECTNAME=?");
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourceId(rs.getLong(2));
				bean.setCourceName(rs.getString(3));
				bean.setSubjectName(rs.getString(4));
				bean.setExamDate(rs.getString(5));
				bean.setExamTime(rs.getString(6));
				bean.setSemester(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting subject by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByName End");
		return bean;
	}

	/**
	 * Find User by College
	 *
	 * @param pk : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public TimeTableBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
		TimeTableBean bean = null;
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourceId(rs.getLong(2));
				bean.setCourceName(rs.getString(3));
				bean.setSubjectName(rs.getString(4));
				bean.setExamDate(rs.getString(5));
				bean.setExamTime(rs.getString(6));
				bean.setSemester(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting subject by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Update a College
	 *
	 * @param bean
	 * @throws DatabaseException
	 */
	public void update(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		//code for getting courcename
        CourceBean cbn=new CourceBean();
        CourceModel cmodel=new CourceModel();
        cbn=cmodel.findByPK(bean.getCourceId());
        //here we set courcename in timetableBean
        bean.setCourceName(cbn.getCourcename());
        
        SubjectBean sbn=new SubjectBean();
        SubjectModel smodel=new SubjectModel();
       sbn =smodel.findByPK(bean.getSubjectId());
       bean.setSubjectName(sbn.getSubjectName());
		TimeTableBean duplicatesubject = findByName(bean.getSubjectName());

		if (duplicatesubject != null) {
            throw new DuplicateRecordException("subject timetable already exists");
        }
		  
		  
		 
		 
		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_TIMETABLE SET COURCEID=?,SUBJECTNAME=?,EXAMDATE=?,EXAMTIME=?,SEMESTER=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getCourceId());

			pstmt.setString(2, bean.getSubjectName());
			pstmt.setString(3, bean.getExamDate());
			pstmt.setString(4, bean.getExamTime());
			pstmt.setString(5, bean.getSemester());

			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Cource ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search College with pagination
	 *
	 * @return list : List of Users
	 * @param bean     : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 *
	 * @throws DatabaseException
	 */
	public List search(TimeTableBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" AND SUBJECTNAME like '" + bean.getSubjectName() + "%'");
			}
			
			if (bean.getCourceName() != null&& bean.getCourceName().length()>0) {
				sql.append(" AND Courcename like '" + bean.getCourceName() + "%'");
			}
			if (bean.getExamDate() != null && bean.getExamDate().length() > 0) {
				sql.append(" AND EXAMDATE like '" + bean.getExamDate() + "%'");
			}
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourceId(rs.getLong(2));
				bean.setCourceName(rs.getString(3));
				bean.setSubjectName(rs.getString(4));
				bean.setExamDate(rs.getString(5));
				bean.setExamTime(rs.getString(6));
				bean.setSemester(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Search College
	 *
	 * @param bean : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(TimeTableBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Get List of College
	 *
	 * @return list : List of College
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of College with pagination
	 *
	 * @return list : List of College
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_TIMETABLE");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TimeTableBean bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourceId(rs.getLong(2));
				bean.setCourceName(rs.getString(3));
				bean.setSubjectName(rs.getString(4));
				bean.setExamDate(rs.getString(5));
				bean.setExamTime(rs.getString(6));
				bean.setSemester(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

}
