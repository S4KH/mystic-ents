package model.traits;

import java.util.ArrayList;
import java.util.HashMap;

public class TraitSet {
	
	//TODO Consider how to make more extensible, ie adding another trait subtype without modifying structure (open closed) but retaining accessability
	
	private Trait healthTrait;
	private Trait moveTrait;
	private Trait attackTrait;
	private Trait damageTrait;
	
	
	public Trait getHealthTrait() {
		return healthTrait;
	}


	public void setHealthTrait(Trait healthTrait) {
		this.healthTrait = healthTrait;
	}


	public Trait getMoveTrait() {
		return moveTrait;
	}


	public void setMoveTrait(Trait moveTrait) {
		this.moveTrait = moveTrait;
	}


	public Trait getAttackTrait() {
		return attackTrait;
	}


	public void setAttackTrait(Trait attackTrait) {
		this.attackTrait = attackTrait;
	}


	public Trait getDamageTrait() {
		return damageTrait;
	}


	public void setDamageTrait(Trait damageTrait) {
		this.damageTrait = damageTrait;
	}

	public TraitSet (Trait healthTrait, Trait moveTrait, Trait attackTrait, Trait damageTrait){
		
		this.healthTrait = healthTrait;
		this.moveTrait = moveTrait;
		this.attackTrait = attackTrait;
		this.damageTrait = damageTrait;
		
	}
}
