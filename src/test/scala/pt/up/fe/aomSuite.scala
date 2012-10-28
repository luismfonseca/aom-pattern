package pt.up.fe

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import pt.up.fe._

@RunWith(classOf[JUnitRunner])
class aomSuite  extends FunSuite {
	test("bigbang is completely empty") {
		assert(BigBang.entities.isEmpty)
		assert(BigBang.entityTypes.isEmpty)
		assert(BigBang.properties.isEmpty)
		assert(BigBang.propertyTypes.isEmpty)
	}
	
	test("adding entity types") {
		val uni = BigBang.Add(EntityType("Procedure"))
		val uni2 = uni.Add(EntityType("ProcedureSuperSpecial"))
		
		assert(uni.entityTypes.size == 1)
		assert(uni.findEntityType("Procedure").isDefined)
		assert(uni2.entityTypes.size == 2)
		assert(uni2.findEntityType("ProcedureSuperSpecial").isDefined)
	}
	
	test("Adding property types") {
		val uni = BigBang.Add(EntityType("Procedure"))
		val uni2 = uni.Add(PropertyType(uni.findEntityType("Procedure").get, "Duration"))
		
		assert(uni2.propertyTypes.size == 1)
		assert(uni2.findPropertyTypes("Duration").isDefined)
	}
}