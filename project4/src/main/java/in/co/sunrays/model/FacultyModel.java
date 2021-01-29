package in.co.sunrays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.bean.CollegeBean;
import in.co.sunrays.bean.CourceBean;
import in.co.sunrays.bean.FacultyBean;
import in.co.sunrays.bean.SubjectBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DatabaseException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.util.JDBCDataSource;

public class FacultyModel {
	private static Logger log = Logger.getLogger(FacultyModel.class);

    /**
     * Find next PK of College
     *
     * @throws DatabaseException
     */
    public long nextPK() throws DatabaseException {
        log.debug("Model nextPK Started");
        Connection conn = null;
        long pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM ST_FACULTY");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getLong(1);
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
    public long add(FacultyBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        long pk = 0;
          CollegeBean bn=new CollegeBean();
		  CollegeModel cmodel=new CollegeModel();
		 bn=cmodel.findByPK(bean.getCollegeId());
          bean.setCollegeName(bn.getName());
		  FacultyBean duplicatesubject= findByName(bean.getFirstName());
		  
		  if (duplicatesubject != null) { throw new
		  DuplicateRecordException("Faculty Name already exists"); }
		 

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO ST_FACULTY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pstmt.setLong(1, pk);
            pstmt.setString(2, bean.getFirstName());
            pstmt.setString(3, bean.getLastName());
            pstmt.setString(4, bean.getJoiningDate());
            pstmt.setString(5, bean.getMobileNo());
            pstmt.setString(6, bean.getLoginId());
            pstmt.setString(7, bean.getGender());
            pstmt.setLong(8, bean.getCollegeId());
            pstmt.setString(9, bean.getCollegeName());
            pstmt.setString(10, bean.getCourcename());
            pstmt.setString(11, bean.getSubjectName());
            pstmt.setString(12, bean.getCreatedBy());
            pstmt.setString(13, bean.getModifiedBy());
            pstmt.setTimestamp(14, bean.getCreatedDatetime());
            pstmt.setTimestamp(15, bean.getModifiedDatetime());
            
            
            
            
          
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
                    "Exception : Exception in add Faculty");
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
    public void delete(FacultyBean bean) throws ApplicationException {
        log.debug("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM ST_FACULTY WHERE ID=?");
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
                    "Exception : Exception in delete faculty");
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
    public FacultyBean findByName(String name) throws ApplicationException {
        log.debug("Model findByFirstname Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_FACULTY WHERE FIRSTNAME=?");
        FacultyBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new FacultyBean();
                bean.setId(rs.getInt(1));
                bean.setFirstName(rs.getString(2));
                bean.setLastName(rs.getString(3));
                bean.setJoiningDate(rs.getString(4));
                bean.setMobileNo(rs.getString(5));
               
               bean.setLoginId(rs.getString(6));
               
               bean.setGender(rs.getString(7));
               bean.setCollegeId(rs.getLong(8));
               bean.setCollegeName(rs.getString(9));
               bean.setCourcename(rs.getString(10));
               bean.setSubjectName(rs.getString(11));
               
                bean.setCreatedBy(rs.getString(12));
                bean.setModifiedBy(rs.getString(13));
                bean.setCreatedDatetime(rs.getTimestamp(14));
                bean.setModifiedDatetime(rs.getTimestamp(15));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting faculty by Name");
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
    public FacultyBean findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_FACULTY WHERE ID=?");
        FacultyBean bean = null;
        Connection conn = null;
        try {

            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	 bean = new FacultyBean();
            	 bean.setId(rs.getInt(1));
                 bean.setFirstName(rs.getString(2));
                 bean.setLastName(rs.getString(3));
                 bean.setJoiningDate(rs.getString(4));
                 bean.setMobileNo(rs.getString(5));
                
                bean.setLoginId(rs.getString(6));
                
                bean.setGender(rs.getString(7));
                bean.setCollegeId(rs.getLong(8));
                bean.setCollegeName(rs.getString(9));
                bean.setCourcename(rs.getString(10));
                bean.setSubjectName(rs.getString(11));
                
                 bean.setCreatedBy(rs.getString(12));
                 bean.setModifiedBy(rs.getString(13));
                 bean.setCreatedDatetime(rs.getTimestamp(14));
                 bean.setModifiedDatetime(rs.getTimestamp(15));
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting faculty by pk");
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
    public void update(FacultyBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Connection conn = null;

        CollegeBean bn=new CollegeBean();
		  CollegeModel cmodel=new CollegeModel();
		 bn=cmodel.findByPK(bean.getCollegeId());
        bean.setCollegeName(bn.getName());

        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE ST_FACULTY SET FIRSTNAME=?,LASTNAME=?,JOININGDATE=?,MOBILENO=?,LOGINID=?,GENDER=?,COLLEGENAME=?,COURSENAME=?,SUBJECTNAME=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
            
            pstmt.setString(1, bean.getFirstName());
            pstmt.setString(2, bean.getLastName());
            pstmt.setString(3, bean.getJoiningDate());
            pstmt.setString(4, bean.getMobileNo());
            pstmt.setString(5, bean.getLoginId());
            pstmt.setString(6, bean.getGender());
            
            pstmt.setString(7, bean.getCollegeName());
            pstmt.setString(8, bean.getCourcename());
            pstmt.setString(9, bean.getSubjectName());
            pstmt.setString(10, bean.getCreatedBy());
            pstmt.setString(11, bean.getModifiedBy());
            pstmt.setTimestamp(12, bean.getCreatedDatetime());
            pstmt.setTimestamp(13, bean.getModifiedDatetime());
            pstmt.setLong(14, bean.getId());
            
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
    public List search(FacultyBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_FACULTY WHERE 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
                sql.append(" AND FIRSTNAME like '" + bean.getFirstName() + "%'");
            }
            if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
                sql.append(" AND COLLEGENAME like '" + bean.getCollegeName() + "%'");
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
                bean = new FacultyBean();
                bean.setId(rs.getInt(1));
                bean.setFirstName(rs.getString(2));
                bean.setLastName(rs.getString(3));
                bean.setJoiningDate(rs.getString(4));
                bean.setMobileNo(rs.getString(5));
               
               bean.setLoginId(rs.getString(6));
               
               bean.setGender(rs.getString(7));
               bean.setCollegeId(rs.getLong(8));
               bean.setCollegeName(rs.getString(9));
               bean.setCourcename(rs.getString(10));
               bean.setSubjectName(rs.getString(11));
               
                bean.setCreatedBy(rs.getString(12));
                bean.setModifiedBy(rs.getString(13));
                bean.setCreatedDatetime(rs.getTimestamp(14));
                bean.setModifiedDatetime(rs.getTimestamp(15));
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
    public List search(FacultyBean bean) throws ApplicationException {
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
        StringBuffer sql = new StringBuffer("select * from ST_FACULTY");
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
                FacultyBean bean = new FacultyBean();
                bean.setId(rs.getInt(1));
                bean.setFirstName(rs.getString(2));
                bean.setLastName(rs.getString(3));
                bean.setJoiningDate(rs.getString(4));
                bean.setMobileNo(rs.getString(5));
               
               bean.setLoginId(rs.getString(6));
               
               bean.setGender(rs.getString(7));
               bean.setCollegeId(rs.getLong(8));
               bean.setCollegeName(rs.getString(9));
               bean.setCourcename(rs.getString(10));
               bean.setSubjectName(rs.getString(11));
               
                bean.setCreatedBy(rs.getString(12));
                bean.setModifiedBy(rs.getString(13));
                bean.setCreatedDatetime(rs.getTimestamp(14));
                bean.setModifiedDatetime(rs.getTimestamp(15));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of faculty");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }
	

}
