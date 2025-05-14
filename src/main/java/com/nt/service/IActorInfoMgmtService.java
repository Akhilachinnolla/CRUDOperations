package com.nt.service;

import java.util.List;

import com.nt.bindings.ActorData;

public interface IActorInfoMgmtService {
	public String registerActorInfo(ActorData data);
	public List<ActorData> showAllActors();
	public ActorData showActorById(int id);
    public String updateActorData(ActorData data);
    public String updateActorRenumeration(int aid,double amount);
    public String updateActorStatus(int aid,String status);
    public String deleteActorById(int aid);
}
