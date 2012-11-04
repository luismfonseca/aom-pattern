package pt.up.fe

object NotMultiverse extends Multiverse(Vector())

class Multiverse
  (val space: Vector[Universe])
{
  
  def apply(that: Universe, thing: Thing) =
    new Multiverse(space.updated(space.indexOf(that), that.Add(thing)))
  
}