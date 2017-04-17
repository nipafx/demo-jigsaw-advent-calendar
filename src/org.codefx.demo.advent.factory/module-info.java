module org.codefx.demo.advent.factory {
	// list the required modules
	requires transitive org.codefx.demo.advent.surprise;
	// export 'AbstractSurpriseFactory' to factory modules
	exports org.codefx.demo.advent.factory
			to org.codefx.demo.advent.factory.chocolate, org.codefx.demo.advent.factory.quote;
}
