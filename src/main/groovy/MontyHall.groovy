class MontyHall {

  static Integer numberOfDoors = 3
  static Integer numberOfTrials = 1000000

  public static void main(String[] args) {
    MontyHall montyHall = new MontyHall()
    Integer numberOfCorrectStays = 0
    Integer numberOfCorrectSwitches = 0

    numberOfTrials.times {
      Integer prizeDoor = montyHall.selectRandomDoor(numberOfDoors)
      Integer guessedDoor = montyHall.selectRandomDoor(numberOfDoors)
      Integer remainingDoor = montyHall.getRemainingDoor(numberOfDoors, guessedDoor, prizeDoor)

      if (remainingDoor == prizeDoor) {
        numberOfCorrectSwitches++
      } else {
        numberOfCorrectStays++
      }
    }

    BigDecimal percentageOfCorrectStays = (numberOfCorrectStays / numberOfTrials) * 100.0
    BigDecimal percentageOfCorrectSwtiches = (numberOfCorrectSwitches / numberOfTrials) * 100.0
    println("Percentage of correct stays: ${percentageOfCorrectStays}")
    println("Percentage of correct switches: ${percentageOfCorrectSwtiches}")
  }

  private static getRemainingDoor(Integer numberOfDoors, Integer guessedDoor, Integer prizeDoor) {
    Integer remainingDoor

    if (numberOfDoors > 2) {
      if (guessedDoor == prizeDoor) {
        List<Integer> allDoors = 0..(numberOfDoors - 1)
        allDoors -= prizeDoor
        remainingDoor = allDoors.get(new Random().nextInt(numberOfDoors - 1))
      } else {
        remainingDoor = prizeDoor
      }
    } else {
      remainingDoor = 0
    }
    return remainingDoor
  }

  private static Long selectRandomDoor(Integer numberOfDoors) {
    return numberOfDoors > 2 ? new Random().nextInt(numberOfDoors) : 0
  }

}
