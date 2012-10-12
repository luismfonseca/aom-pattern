package pt.up.fe

object test {
  
	/* Model */
	val procedure = new EntityType("Procedure");
	val duration = new PropertyType(procedure, "Duration");
	
	/* Data */
	val p1 = new Entity(procedure, "Surgery");
	val p1Duration = new Property(p1, duration, "00:35");
	
	/* Modifying Model */
	val description = new PropertyType(procedure, "Description");
                
	/* Updating Data */
	val p1Description = new Property(p1, description, "Tooth Removal");

    // Universe like
	val bigBang = Universe.empty
	val finalUniverse = bigBang
	  	.newEntityType(procedure)
		.newPropertyType(duration)
		.newEntity(p1)
		.newPropertyType(description)
		.newProperty(p1Description)
	 
	def showMeTheUniverse = {
	  finalUniverse.entities foreach println
	}
}