package com.ddf.capital.dao;






import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.query.BondOrderRefundApplyQuery;
import com.ddf.entity.capital.vo.BondOrderRefundApplyVo;
import com.ddf.entity.capital.dto.BondOrderRefundApply;

/**
 * bond_order_refund_apply DAO接口
 * @author robot
 * @version 2018-01-22
 */
public interface BondOrderRefundApplyDao extends CrudDao<BondOrderRefundApply,BondOrderRefundApplyVo,BondOrderRefundApplyQuery> {
}