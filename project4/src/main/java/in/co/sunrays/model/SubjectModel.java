package in.co.sunrays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.bean.CourceBean;
import in.co.sunrays.bean.SubjectBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DatabaseException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.util.JDBCDataSource;
import in.co.sunrays.model.*;
public class SubjectModel {
	private static Logger log = Logger.getLogger(SubjectModel.class);

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
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM ST_SUBJECT");
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
    public long add(SubjectBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;
          CourceBean bn=new CourceBean();
		  CourceModel cmodel=new CourceModel();
		 bn=cmodel.findByPK(bean.getCourceId());

		  SubjectBean duplicatesubject= findByName(bean.getSubjectCode());
		  
		  if (duplicatesubject != null) { throw new
		  DuplicateRecordException("Subject Name already exists"); }
		 

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO ST_SUBJECT VALUES(?,?,?,?,?,?,?,?,?,?)");
            bean.setCourceName(bn.getCourcename());
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getSubjectCode());
            pstmt.setString(3, bean.getCourceName());
            pstmt.setLong(4, bean.getCourceId());
            pstmt.setString(5, bean.getSubjectName());
            pstmt.setString(6, bean.getDescription());
            pstmt.setString(7, bean.getCreatedBy());
            pstmt.setString(8, bean.getModifiedBy());
            pstmt.setTimestamp(9, bean.getCreatedDatetime());
            pstmt.setTimestamp(10, bean.getModifiedDatetime());
            
            
            
            
          
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in add Subject");
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
    public void delete(SubjectBean bean) throws ApplicationException {
        log.debug("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM ST_SUBJECT WHERE ID=?");
            pstmt.setLong(1, bean.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in delete college");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model delete Started");
    }

    /**
     * Find User by College
     *
     * @param login
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */
    public SubjectBean findByName(String name) throws ApplicationException {
        log.debug("Model findBySUBJECTCODE Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_SUBJECT WHERE SUBJECTCODE=?");
        SubjectBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new SubjectBean();
                bean.setId(rs.getInt(1));
                bean.setSubjectCode(rs.getString(2));
                bean.setCourceName(rs.getString(3));
                bean.setCourceId(rs.getInt(4));
               bean.setSubjectName(rs.getString(5));
               bean.setDescription(rs.getString(6));
                bean.setCreatedBy(rs.getString(7));
                bean.setModifiedBy(rs.getString(8));
                bean.setCreatedDatetime(rs.getTimestamp(9));
                bean.setModifiedDatetime(rs.getTimestamp(10));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting subject by Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByName End");
        return bean;
    }

    /**
     * Find User by College
     *
     * @param pk
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */
    public SubjectBean findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_SUBJECT WHERE ID=?");
       SubjectBean bean = null;
        Connection conn = null;
        try {

            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	 bean = new SubjectBean();
                 bean.setId(rs.getInt(1));
                 bean.setSubjectCode(rs.getString(2));
                 bean.setCourceName(rs.getString(3));
                 bean.setCourceId(rs.getInt(4));
                bean.setSubjectName(rs.getString(5));
                bean.setDescription(rs.getString(6));
                 bean.setCreatedBy(rs.getString(7));
                 bean.setModifiedBy(rs.getString(8));
                 bean.setCreatedDatetime(rs.getTimestamp(9));
                 bean.setModifiedDatetime(rs.getTimestamp(10));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting subject by pk");
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
    public void update(SubjectBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Connection conn = null;

        SubjectBean beanExist = findByName(bean.getSubjectCode());
System.out.println(bean.getSubjectCode());
        // Check if updated College already exist
        if (beanExist != null && beanExist.getId() == bean.getId()) {

            throw new DuplicateRecordException("SubjectCode is already exist");
        }

        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE ST_SUBJECT SET SUBJECTCODE=?,COURCEID=?,SUBJECTNAME=?,DISCRIPTION=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
            pstmt.setString(1, bean.getSubjectCode());
            pstmt.setLong(2, bean.getCourceId());
            pstmt.setString(3, bean.getSubjectName());
            pstmt.setString(4, bean.getDescription());
            pstmt.setString(5, bean.getCreatedBy());
            pstmt.setString(6, bean.getModifiedBy());
            pstmt.setTimestamp(7, bean.getCreatedDatetime());
            pstmt.setTimestamp(8, bean.getModifiedDatetime());
            pstmt.setLong(9, bean.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
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
     * @param bean
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     * @throws DatabaseException
     */
    public List search(SubjectBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_SUBJECT WHERE 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
                sql.append(" AND SUBJECTNAME like '" + bean.getSubjectName() + "%'");
            }
            if (bean.getSubjectCode() != null && bean.getSubjectCode().length() > 0) {
                sql.append(" AND SUBJECTCODE like '" + bean.getSubjectCode() + "%'");
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
                bean = new SubjectBean();
                bean.setId(rs.getLong(1));
                bean.setSubjectCode(rs.getString(2));
                bean.setCourceName(rs.getString(3));
                
                bean.setCourceId(rs.getInt(4));
               bean.setSubjectName(rs.getString(5));
               bean.setDescription(rs.getString(6));
                bean.setCreatedBy(rs.getString(7));
                bean.setModifiedBy(rs.getString(8));
                bean.setCreatedDatetime(rs.getTimestamp(9));
                bean.setModifiedDatetime(rs.getTimestamp(10));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search college");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Search College
     *
     * @param bean
     *            : Search Parameters
     * @throws DatabaseException
     */
    public List search(SubjectBean bean) throws ApplicationException {
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
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from ST_SUBJECT");
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
                SubjectBean bean = new SubjectBean();
                bean.setId(rs.getInt(1));
                bean.setSubjectCode(rs.getString(2));
                bean.setCourceName(rs.getString(3));
                
                bean.setCourceId(rs.getInt(4));
               bean.setSubjectName(rs.getString(5));
               bean.setDescription(rs.getString(6));
                bean.setCreatedBy(rs.getString(7));
                bean.setModifiedBy(rs.getString(8));
                bean.setCreatedDatetime(rs.getTimestamp(9));
                bean.setModifiedDatetime(rs.getTimestamp(10));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of users");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }
	

}
