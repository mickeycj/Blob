package base;

import processing.core.PVector;

public interface Unit extends Component {

	PVector getPosition();
	
	float getRadius();
	
	boolean eat(Unit other);
	
	void update();
}
