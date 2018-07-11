package com.sawelly.utils;
      
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
      
/** 
 * 连接数据库的工具类,被定义成不可继承且是私有访问 
 * @author lanp 
 * @since 2012-2-29 22:27 
 */  
public final class DBUtils {  
        private  String url; 
        private  String user;  
        private  String psw; 
          
        private Connection conn;
        private Statement stat;  
          
        static {  
            try {  
                Class.forName("com.mysql.jdbc.Driver");  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            }  
        }  
          
        public DBUtils() {  
              
        }  
        
        public DBUtils(String url,String user,String psw) {  
        	this.url = url;
        	this.user = user;
        	this.psw = psw;
        }  
          
        /** 
         * 获取数据库的连接 
         * @return conn 
         */  
        public Connection getConnection() {  
            if(null == conn) {  
                try {  
                    conn = DriverManager.getConnection(url, user, psw);  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                    throw new RuntimeException(e);  
                }  
            }  
            return conn;  
        }  
        
        /** 
         * 释放资源 
         * @param conn 
         * @param pstmt 
         * @param rs 
         */  
        public static void closeResources(Connection conn,PreparedStatement pstmt,ResultSet rs) {  
            if(null != rs) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                    throw new RuntimeException(e);  
                } finally {  
                    if(null != pstmt) {  
                        try {  
                            pstmt.close();  
                        } catch (SQLException e) {  
                            e.printStackTrace();  
                            throw new RuntimeException(e);  
                        } finally {  
                            if(null != conn) {  
                                try {  
                                    conn.close();  
                                } catch (SQLException e) {  
                                    e.printStackTrace();  
                                    throw new RuntimeException(e);  
                                }  
                            }  
                        }  
                    }  
                }  
            }  
        }  
        

        /** 
         * 释放资源 
         * @param conn 
         * @param pstmt 
         */  
        public void closeResources(Connection conn,Statement pstmt) {  
            if(null != pstmt) {  
                try {  
                    pstmt.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                    throw new RuntimeException(e);  
                } finally {  
                    if(null != conn) {  
                        try {  
                            conn.close();  
                        } catch (SQLException e) {  
                            e.printStackTrace();  
                            throw new RuntimeException(e);  
                        }  
                    }  
                }  
            }  
        }
    }  