package com.github.sameersingh.bibere

import org.junit.Test
import org.junit.Assert._
import java.nio.file.Files

/**
 * @author sameer
 */
class WritingTest {

  def synthPubs: Publications = {
    val result = new Publications
    // authors
    result += Author("sameer", PersonName("Sameer", "Singh"), Some("http://www.cs.umass.edu/~sameer"))
    result += Author("andrew", PersonName("Andrew", "McCallum"), Some("http://www.cs.umass.edu/~mccallum"))
    // venues
    result += Venue("icml", "International Conference on Machine Learning (ICML)", "ICML")
    result += Venue("biglearn", "NIPS Workshop on Big Learning", "BigLearning")
    // papers
    result += Paper("biglearn11", "Towards Asynchronous Distributed MCMC Inference for Large Graphical Models", PubType.Workshop, 2011, Seq("sameer", "andrew"),
      "biglearn")

    result
  }

  @Test
  def testCaseClassWriting() {
    val testDir = Files.createTempDirectory("bibere").toString
    println(testDir)
    JsonWriter.write(synthPubs, testDir)
    val pubs = JsonReader.readPublications(testDir)
    println("authors: " + pubs.authors.values)
    println("venues: " + pubs.venues.values)
    println("papers: " + pubs.papers.values)
  }
}
