module org.codefx.demo.advent {
	// list the required modules
	requires org.codefx.demo.advent.calendar;
	// with 'static' the factories are only required at compile time;
	// to be present at run time either other modules most require them
	// or they must be added with the '--add-modules' command line option
	requires static org.codefx.demo.advent.factory.chocolate;
	requires static org.codefx.demo.advent.factory.quote;
}
