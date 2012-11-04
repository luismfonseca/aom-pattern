package pt.up.fe

object NotMultiverse extends Multiverse(Vector())

object Multiverse {
  type Universe = Array[Set[Thing]]
}

class Multiverse
  (val space: Vector[Universe])
{
  
  def apply(that: Universe, thing: Thing) = {
    
    new Multiverse(
      space.updated(space.indexOf(that),
      Thing.AddSomething(thing)
      	(t => {
      	  that.Add(t)
      	}))
    )
  }
}