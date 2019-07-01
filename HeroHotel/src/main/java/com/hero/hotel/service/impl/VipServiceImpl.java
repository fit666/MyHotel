package com.hero.hotel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hero.hotel.pojo.Vip;
import org.springframework.stereotype.Service;

import com.hero.hotel.dao.VipDao;
import com.hero.hotel.service.VipService;


@Service("/vipService")
public class VipServiceImpl implements VipService{
	@Resource
	private VipDao vipDao;
	@Override
	public List<Vip> queryAllVip() {
		// TODO Auto-generated method stub
		return vipDao.findAllVips();
	}
	/*
	 * 获取所有的vip信息(non-Javadoc)
	 * @see com.hero.hotel.service.VipService#findAllVips()
	 */
	@Override
	public List<Vip> findAllVips() {
		
		return vipDao.findAllVips();
	}
	/*
	 * 修改vip信息(non-Javadoc)
	 * @see com.hero.hotel.service.VipService#editVip(com.hero.hotel.pojo.Vip)
	 */
	@Override
	public String editVip(Vip vip) {
		boolean boo=vipDao.updateVip(vip);
		if(boo){
			return "修改成功";
		}
		return "修改失败，请联系相关工作人员";
	}
	@Override
	public String vipDel(Vip vip) {
		boolean r=vipDao.vipDel(vip);
		if(r){
			return "删除成功";
		}
		return "删除失败，请联系相关工作人员";
	}
	/*
	 * 添加vip(non-Javadoc)
	 * @see com.hero.hotel.service.VipService#addVip(com.hero.hotel.pojo.Vip)
	 */
	@Override
	public boolean addVip(Vip vip) {
		
		return vipDao.addVip(vip);
	}

}
