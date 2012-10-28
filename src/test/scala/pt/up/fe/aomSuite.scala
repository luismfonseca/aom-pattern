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
		assert(uni2.entityTypes.size == 2)
	}
}