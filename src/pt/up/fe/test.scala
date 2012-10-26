package pt.up.fe

object test {
  
	/* Model */
	val procedure = EntityType("Procedure")
	val duration = PropertyType(procedure, "Duration")
	
	/* Data */
	val p1 = Entity(procedure, "Surgery")
	val p1Duration = Property(p1, duration, "00:35")
	
	/* Modifying Model */
	val description = PropertyType(procedure, "Description")
                
	/* Updating Data */
	val p1Description = Property(p1, description, "Tooth Removal")
	
	
    // Now, Universe like
	/* Model */
	val uni2 = BigBang.Add(EntityType("Procedure"))
	val uni3 = uni2.Add(PropertyType(uni2.findEntityType("Procedure"), "Duration"))
	
	/* Data */
	val uni4 = uni3.Add(Entity(uni3.findEntityType("Procedure"), "Surgery"))
	val uni5 = uni4.Add(Property(uni4.findEntity("Surgery"),
							     uni4.findPropertyTypes("Duration"),
							     "00:35"))

	/* Modifying Model */
    val uni6 = uni5.Add(PropertyType(uni5.findEntityType("Procedure"), "Description"))
    
	/* Updating Data */
	val uni7 = uni6.Add(Property(uni6.findEntity("Surgery"),
								 uni6.findPropertyTypes("Description"),
								 "Tooth Removal"))
	
	def showMeTheUniverse = {
	  uni7.entities foreach println
	}
}

object main {
    def main(args: Array[String]) {
      println("Universe:")
  
	  val uni1 = BigBang.Add(EntityType("Procedure"))
	  val uni2 = uni1.Add(EntityType("ProcedureSpecial"))
	  val uni3 = uni2.Add(EntityType("ProcedureSuperSpecial"))
	  
	  uni3.entityTypes foreach println
      println("Revert to uni2:")
	  uni3.revert.entityTypes foreach println
	  
      println("Revert to uni1:")
	  uni3.revert.revert.entityTypes foreach println
	  
      println("Revert to bb:")
	  uni3.revert.revert.revert.entityTypes foreach println
	  
	  println("branching & merging")
      val uni3_sideuniverse = uni2.Add(EntityType("ProcedureAlternative"))
      val uni3merged = uni3.merge(uni3_sideuniverse)
      
	  println("result (uni3merged):")
	  uni3merged.entityTypes foreach println
	  
	  println("new entity to uni3merged")
      val uni4 = uni3merged.Add(Entity(uni3merged.entityTypes.find(_.name == "Procedure").get, "Surgery"))
      
	  uni4.entities foreach println
	  uni4.entityTypes foreach println
	  
	  println("reverting to uni3merged")
	  uni4.revert.entities foreach println
	  uni4.revert.entityTypes foreach println
	  
	  println("adding propertyType duration")
      val uni5 = uni4.Add(PropertyType(uni3merged.entityTypes.find(_.name == "Procedure").get, "Duration"))
	  uni5.entities foreach println
	  uni5.entityTypes foreach println
	  uni5.propertyTypes foreach println
	  
	  println("reverting propertyType duration")
	  uni5.revert.entities foreach println
	  uni5.revert.entityTypes foreach println
	  uni5.revert.propertyTypes foreach println
	  
	  println("example demo")
	  test.showMeTheUniverse
    }
}