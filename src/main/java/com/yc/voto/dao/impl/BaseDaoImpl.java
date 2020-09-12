package com.yc.voto.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yc.voto.dao.IBaseDao;

public class BaseDaoImpl implements IBaseDao {
	private static SqlSessionFactory factory = null;
	
	static {
		try(InputStream is = Resources.getResourceAsStream("mybatis-config.xml")){
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		private void close(SqlSession session) {
			if(session != null) {
				session.close();
			}		
	}
	@Override
	public int insert(String sqlId, Object obj) {
		SqlSession session = null;
		int result = -1;
			
			try {
				session = factory.openSession();
				result = session.insert(sqlId,obj);
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				this.close(session);
			}	
		return result;
	}

	@Override
	public int update(String sqlId, Object obj) {
		SqlSession session = null;
		int result = -1;
			
			try {
				session = factory.openSession();
				result = session.insert(sqlId,obj);
				session.commit();
			} catch (Exception e) {
				session.rollback();
				e.printStackTrace();
			}finally {
				this.close(session);
			}	
		return result;
	}

	@Override
	public int update(List<String> sqlIds, List<Object> obj) {
		SqlSession session = null;
		int result = -1;
			
			try {
				session = factory.openSession();
				for(int i = 0,len = sqlIds.size();i<len;i ++) {
					result = session.insert(sqlIds.get(i),obj.get(i));
				}
				session.commit();
			} catch (Exception e) {
				session.rollback();
				result=0;
				e.printStackTrace();
			}finally {
				this.close(session);
			}	
		return result;
	}

	@Override
	public int delete(String sqlId, Object obj) {
		SqlSession session = null;
		int result = -1;
			
			try {
				session = factory.openSession();
				result = session.delete(sqlId,obj);
				session.commit();
			} catch (Exception e) {
				session.rollback();
				e.printStackTrace();
			}finally {
				this.close(session);
			}	
		return result;
	}

	@Override
	public <T> T find(String sqlId, Object obj) {
		SqlSession session = null;
			
			try {
				session = factory.openSession();
				return session.selectOne(sqlId,obj);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				this.close(session);
			}	
		return null;
	}

	@Override
	public <T> List<T> finds(String sqlId) {
		SqlSession session = null;
		
		try {
			session = factory.openSession();
			return session.selectList(sqlId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.close(session);
		}	
	return null;
	}

	@Override
	public <T> List<T> finds(String sqlId, Object obj) {
		SqlSession session = null;
		
		try {
			session = factory.openSession();
			return session.selectList(sqlId,obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.close(session);
		}	
	return null;
	}

	@Override
	public int total(String sqlId) {
		SqlSession session = null;
		
		try {
			session = factory.openSession();
			return session.selectOne(sqlId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.close(session);
		}	
	return 0;
	}

	@Override
	public int total(String sqlId, Object obj) {
		SqlSession session = null;
		
		try {
			session = factory.openSession();
			return session.selectOne(sqlId,obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.close(session);
		}	
	return 0;
	}

}
