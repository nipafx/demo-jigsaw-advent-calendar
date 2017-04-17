module org.codefx.demo.advent.factory.quote {
	// list the required modules
	requires org.codefx.demo.advent.factory;
	requires org.codefx.demo.advent.surprise;
	// specify which class provides which service
	provides org.codefx.demo.advent.surprise.SurpriseFactory
		with org.codefx.demo.advent.factory.quote.QuoteFactory;
}
