package com.pl23k.restaurant.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.TableMapping;
import com.pl23k.restaurant.model.base.BaseConsume;

import java.math.BigDecimal;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Consume extends BaseConsume<Consume> {
	public static final Consume dao = new Consume().dao();
	public static final String tableName = TableMapping.me().getTable(Consume.class).getName();
	public static int mPageSize = 10;

	/**
	 * 通过ID获取记录
	 * @param id
	 * @return
	 */
	public static Consume getRecordById(Integer id){
		Consume record = null;
		try{
			record = dao.findById(id);
		}catch (Exception e){
			e.printStackTrace();
		}
		return record;
	}

	/**
	 * 分页取出记录
	 * @param pageNumber
	 * @return
	 */
	public static Page<Consume> getRecordByPage(int pageNumber){
		Page<Consume> page = null;
		try {
			page = dao.paginate(pageNumber, mPageSize, "select * ", String.format("from %s order by id desc", tableName));
		}catch (Exception e){
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 分页取出记录
	 * @param pageNumber
	 * @param workerId
	 * @return
	 */
	public static Page<Consume> getRecordByPageWorkerId(int pageNumber,Integer workerId){
		Page<Consume> page = null;
		try {
			page = dao.paginate(pageNumber, mPageSize, "select * ", String.format("from %s where workerId=? order by id desc", tableName),workerId);
		}catch (Exception e){
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 分页查询记录列表
	 *
	 * @param pageNumber
	 * @param searchType 1、按消费者者用户名
	 * @param searchKey
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Page<Consume> searchRecordByPage(Integer pageNumber,Integer searchType, String searchKey, String startTime, String endTime) {
		Page<Consume> page = null;
		try {
			if(pageNumber==null || pageNumber<1) pageNumber=1;
			String where;
			if(searchType == null) searchType = 1;
			if(StrKit.isBlank(searchKey)){
				where = String.format("from %s where id > 0 ", tableName);
			}else{
				if(searchType == 1){
					where = String.format("from %s where remark like '%%%s%%' ", tableName, searchKey);
				}else{
					where = String.format("from %s where  id>0 ", tableName);
				}
			}

			if (startTime != null && !startTime.equals("") && endTime != null && !endTime.equals("")) {
				where = String.format("%s and (addTime between '%s' and '%s')", where, startTime.replace('_', '-'), endTime.replace('_', '-'));
			}

			where = String.format("%s order by id desc", where);
			page = dao.paginate(pageNumber, mPageSize, "select * ", where);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 获取充值者姓名
	 * @return
	 */
	public String getMemberRealName(){
		return User.getRealNameByMemberId(getMemberId());
	}

	public String getMemberUsername(){
		return User.getUsernameByMemberId(getMemberId());
	}

	public String getRejectRealName(){
		return Admin.getRealNameById(getRejectAdmin());
	}

	public String getRejectUsername(){
		return Admin.getUsernameById(getRejectAdmin());
	}

	/**
	 * 获取总消费
	 * @return
	 */
	public static BigDecimal getConsumeTotal(){
		BigDecimal total = null;
		try{
			Record record = Db.findFirst(String.format("select sum(totalMoney) as total from %s where status=0",tableName));
			if(record!=null && record.get("total")!=null){
				total = record.getBigDecimal("total");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 获取总消费
	 * @param workerId
	 * @return
	 */
	public static BigDecimal getConsumeTotalByWorkerId(Integer workerId){
		BigDecimal total = null;
		try{
			Record record = Db.findFirst(String.format("select sum(totalMoney) as total from %s where status=0 and workerId=?",tableName),workerId);
			if(record!=null && record.get("total")!=null){
				total = record.getBigDecimal("total");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 获取今日消费
	 * @return
	 */
	public static BigDecimal getConsumeTodayTotal(){
		BigDecimal total = null;
		try{
			Record record = Db.findFirst(String.format("select sum(totalMoney) as total from %s where status=0 and to_days(addTime)=to_days(now())",tableName));
			if(record!=null && record.get("total")!=null){
				total = record.getBigDecimal("total");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 获取今日消费
	 * @param workerId
	 * @return
	 */
	public static BigDecimal getConsumeTodayTotalByWorkerId(Integer workerId){
		BigDecimal total = null;
		try{
			Record record = Db.findFirst(String.format("select sum(totalMoney) as total from %s where status=0 and workerId=? and to_days(addTime)=to_days(now())",tableName),workerId);
			if(record!=null && record.get("total")!=null){
				total = record.getBigDecimal("total");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 获取总真实消费
	 * @return
	 */
	public static BigDecimal getConsumeTotaReal(){
		BigDecimal total = null;
		try{
			Record record = Db.findFirst(String.format("select sum(payTotalMoney) as total from %s where status=0",tableName));
			if(record!=null && record.get("total")!=null){
				total = record.getBigDecimal("total");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 获取今日消费
	 * @return
	 */
	public static BigDecimal getConsumeTodayTotalReal(){
		BigDecimal total = null;
		try{
			Record record = Db.findFirst(String.format("select sum(payTotalMoney) as total from %s where status=0 and to_days(addTime)=to_days(now())",tableName));
			if(record!=null && record.get("total")!=null){
				total = record.getBigDecimal("total");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 获取用户总消费
	 * @return
	 */
	public static BigDecimal getTotalConsumeByMemberId(String memberId){
		BigDecimal total = null;
		try{
			Record record = Db.findFirst(String.format("select sum(totalMoney) as total from %s where status=0 and memberId=?",tableName),memberId);
			if(record!=null && record.get("total")!=null){
				total = record.getBigDecimal("total");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 获取用户今日消费
	 * @return
	 */
	public static BigDecimal getTodayConsumeByMemberId(String memberId){
		BigDecimal total = null;
		try{
			Record record = Db.findFirst(String.format("select sum(totalMoney) as total from %s where status=0 and to_days(addTime)=to_days(now()) and memberId=?",tableName),memberId);
			if(record!=null && record.get("total")!=null){
				total = record.getBigDecimal("total");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 获取给用户看的介绍
	 * @return
	 */
	public String getUserRemark(){
		String remark = getRemark();
		if(remark.lastIndexOf("，")>0){
			remark = remark.substring(0,remark.lastIndexOf("，"));
		}
		return remark;
	}
}
