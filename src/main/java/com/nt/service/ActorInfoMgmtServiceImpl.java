package com.nt.service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.bindings.ActorData;
import com.nt.repository.IActorInfoRepository;
import org.springframework.http.HttpStatus;

import com.nt.service.*;
import com.nt.entity.ActorInfoEntity;

@Service
public class ActorInfoMgmtServiceImpl implements IActorInfoMgmtService {
    @Autowired
	private IActorInfoRepository actorRepo;
    @Value("${user.name}")
    private String osuser;
	
    @Override
	public String registerActorInfo(ActorData data) {
        //convert binding object data to entity object data
    	ActorInfoEntity entity=new ActorInfoEntity();
    	BeanUtils.copyProperties(data,entity);
    	//set data Meta to Entity object
    	entity.setCreatedBy(osuser);
    	entity.setUpdatedBy(osuser);
    	  //save the entity object
    	int idVal=actorRepo.save(entity).getAid();
		return "Actor is saved with the id value::"+idVal;
		
	}
    public List<ActorData> showAllActors(){
    	//use repo
    	List<ActorInfoEntity> listEntities=actorRepo.findAll();
    	//convert List of entities to list of binding of class onjs
    	/*List<ActorData> listData=new ArrayList();
    	for(ActorInfoEntity entity:listEntities) {
    		ActorData data=new ActorData();
    		BeanUtils.copyProperties(entity,data);
    		listData.add(data);
    	}*/
    	
    	//using streamAPI
    	
    	List<ActorData> listData=listEntities.stream().map(entity->{ActorData data=new ActorData();
    	BeanUtils.copyProperties(entity,data);
    	return data;}).collect(Collectors.toList());

    	return listData;
    }
    @Override
    public ActorData showActorById(int id) {
    	//use repo
    	ActorInfoEntity entity=actorRepo.findById(id).orElseThrow(()->new IllegalArgumentException("invalid id"));
    	//copy entity object data to binding object
    	ActorData data=new ActorData();
    	BeanUtils.copyProperties(entity,data);
    	return data;
    }
    
    @Override
    public String updateActorData(ActorData data) {
    	ActorInfoEntity entity=actorRepo.findById(data.getAid()).orElseThrow(()->new IllegalArgumentException("invalid id"));
    	//copy ActorData obj data to ActorInfo obj
    	BeanUtils.copyProperties(data,entity);
    	entity.setUpdatedBy(osuser);
    	//update the object
    	int idVal=actorRepo.save(entity).getAid();
    	return idVal+"Actor Data is updated";
    	
    }
    
    @Override
    public String updateActorRenumeration(int aid,double amount) {
    	//use repo
    	ActorInfoEntity entity=actorRepo.findById(aid).orElseThrow(()->new IllegalArgumentException("invalid id"));
    	//modify entity object
    	entity.setRemuneration(amount);
    	//update the object
    	int idVal=actorRepo.save(entity).getAid();
    	return idVal+"Actor Remuneration is updated";
    	
    }
    
    @Override
    public String updateActorStatus(int aid,String status) {
    	//use repo
    	ActorInfoEntity entity=actorRepo.findById(aid).orElseThrow(()->new IllegalArgumentException("invalid id"));
    	//modify entity object
    	entity.setActive_SW(status);
    	//update the object
    	int idVal=actorRepo.save(entity).getAid();
    	return idVal+"Actor Status is updated";
    
    }
    
    @Override
    public String deleteActorById(int aid) {
    	//use repo
    	ActorInfoEntity entity=actorRepo.findById(aid).orElseThrow(()->new IllegalArgumentException("invalid id"));
        //delete object
    	actorRepo.deleteById(aid);
    	return aid+"Actor is deleted";
    }
    
    
   
}
