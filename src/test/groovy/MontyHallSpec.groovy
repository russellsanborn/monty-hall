import spock.lang.Specification

class MontyHallSpec extends Specification {

  MontyHall montyHall

  def setup() {
    montyHall = new MontyHall()
    GroovyMock(Random, global: true)
  }

  def "test when a door is selected return remaining door number"() {
    setup:
    Integer numberOfDoors = 3
    Integer prizeDoor = 1
    Random mockRandom = Mock()

    when: "the wrong door is guessed"
    Integer remainingDoor = montyHall.getRemainingDoor(numberOfDoors, 0, prizeDoor)

    then: "the reamining door is the prize door"
    0 * _
    remainingDoor == prizeDoor

    when: "the right door is guessed"
    remainingDoor = montyHall.getRemainingDoor(numberOfDoors, prizeDoor, prizeDoor)

    then: "the remaining door is any single non prize door"
    1 * new Random() >> mockRandom
    1 * mockRandom.nextInt(numberOfDoors - 1)
    0 * _
    remainingDoor != prizeDoor

    when: "there are less than 3 doors"
    remainingDoor = montyHall.getRemainingDoor(2, prizeDoor, prizeDoor)

    then: "door 0 is returned"
    0 * _
    remainingDoor == 0
  }

  def "test selecting a random door"() {
    setup:
    Random mockRandom = Mock()

    when: "selecting a new door"
    Integer selectedDoor = montyHall.selectRandomDoor(numberOfDoors)

    then: "the door should be between 0 and the number of doors"
    numberOfRandomCalls * new Random() >> mockRandom
    numberOfRandomCalls * mockRandom.nextInt(numberOfDoors) >> expectedDoor
    0 * _
    selectedDoor == expectedDoor

    where:
    numberOfDoors | numberOfRandomCalls | expectedDoor
    0             | 0                   | 0
    2             | 0                   | 0
    3             | 1                   | 3
    4             | 1                   | 4
    -1            | 0                   | 0
    null          | 0                   | 0
  }

}
