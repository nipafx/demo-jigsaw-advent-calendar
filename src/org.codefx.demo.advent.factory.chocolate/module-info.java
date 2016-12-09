module org.codefx.demo.advent.factory.chocolate {
	// list the required modules
	requires transitive org.codefx.demo.advent.surprise;
	// specify which class provides which service
	provides org.codefx.demo.advent.surprise.SurpriseFactory
		with org.codefx.demo.advent.factory.chocolate.ChocolateFactory;
}
