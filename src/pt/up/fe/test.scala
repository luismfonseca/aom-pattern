package pt.up.fe

object test {
  
	/* Model */
	val procedure = EntityType("Procedure");
	val duration = PropertyType(procedure, "Duration");
	
	/* Data */
	val p1 = Entity(procedure, "Surgery");
	val p1Duration = Property(p1, duration, "00:35");
	
	/* Modifying Model */
	val description = PropertyType(procedure, "Description");
                
	/* Updating Data */
	val p1Description = Property(p1, description, "Tooth Removal");

    // Universe like
	val finalUniverse = BigBang
	  	.newEntityType(procedure)
		.newPropertyType(duration)
		.newEntity(p1)
		.newPropertyType(description)
		.newProperty(p1Description)
	 
	def showMeTheUniverse = {
	  finalUniverse.entities foreach println
	}
}

object main {
    def main(args: Array[String]) {
      println("Universe:")
      test.showMeTheUniverse
    }
}