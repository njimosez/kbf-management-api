package com.kbf.management.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum ProvenderType {
	@JsonAlias({ "fish", "FISH" })
	FISH,
	POULTRY, 
	DOG,
	LARVA,
	FINGERLINGS,
	JUVENILE,
	ADULT,
	BROODSTOCK

}
