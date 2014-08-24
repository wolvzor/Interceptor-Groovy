import spock.lang.Specification
import com.wolviegames.interceptor.gameobject.Pilot

class PilotSpec extends Specification{
	
	def "Test piloting skill generation"() {
		setup:
		Pilot pilot = new Pilot("test","dummy")
		
		expect:
		pilot.generatePilotingSkill(randomNumber)
		pilot.getPilotingSkill() == pilotingSkill
		
		where:
		randomNumber || pilotingSkill
		0 || 6
		1 || 5
		2 || 5
		3 || 5
		4 || 5
		5 || 5
		6 || 5
		7 || 5
		8 || 4
		9 || 3
	}
	
	def "Test gunnery skill generation"() {
		setup:
		Pilot pilot = new Pilot("test","dummy")
		
		expect:
		pilot.generateGunnerySkill(randomNumber)
		pilot.getGunnerySkill() == gunnerySkill
		
		where:
		randomNumber || gunnerySkill
		0 || 6
		1 || 5
		2 || 4
		3 || 4
		4 || 4
		5 || 4
		6 || 4
		7 || 4
		8 || 3
		9 || 2
	}

}
