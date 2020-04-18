import math._
import scala.util._
import scala.io.StdIn._

object Player extends App {
    val projectCount = readLine.toInt
    for(i <- 0 until projectCount) {
        val Array(a, b, c, d, e) = (readLine split " ").map (_.toInt)
    }

    // if we are already at the module, we just connect to sample. otherwise we go to module. 
    // here, sample can be sample id or a molecule.
    def goOrConnect(module : String, sample : String, target : String) {
        if (module == target) {
            println("CONNECT " + sample)
        }
        else {
            println("GOTO " + module)
        }
    }

    // if we're already carrying one of the samples, we'll return it as the bigger one.
    // so we'll just find and take the best samples only if we are not carrying any. 
    def findMax(input1 : Array[String], input2 : Array[String]) : Array[String] = {
        if (input1(1).toInt == 0) {
            return input1
        }
        if (input2(1).toInt == 0) {
            return input2
        }
        if (input1(4).toInt > input2(4).toInt && input1(1).toInt != 1) {
            return input1
        }
        return input2
    }

    // it simply loops through the samples recursively until it finds the best one.
    def findBestSample(sample_count : Int, sample : Array[String], target : String) : Array[String] = {
        if (sample_count == 0) {
            if (sample(1).toInt != 0) {
                goOrConnect("DIAGNOSIS", sample(0), target)
                return Array("done")
            }
            return sample
        }
        return findBestSample(
            sample_count-1,
            // next input will be a new sample
            findMax(sample, readLine split " "),
            target
        )
    }

    // it checks whether we need any molecule or not
    def findMolecule(best_sample : Array[String], count : Int, player : Array[String]) : Unit = {
        if (best_sample(0) == "done") {
            return
        }
        if (count == 5) {
            goOrConnect("LABORATORY", best_sample(0), player(0))
            return
        }
        if (best_sample(5+count).toInt >= player(3+count).toInt) {
            goOrConnect("MOLECULES", "ABCDE".substring(count, count+1), player(0))
            return
        }
        return findMolecule(best_sample, count+1, player)
    }

    // since the program takes inputs in a fixed order, we discard the not needed inputs.
    def decide(player : Array[String]) {
        readLine split " "
        readLine split " "
        findMolecule(
            findBestSample(
                // this input will be sample_count
                readLine.toInt-1,
                // this input is the first sample
                readLine split " ",
                player(0)
            ),
            0,
            player
        )
    }
    // game loop
    while(true) {
        decide(readLine split " ")
    }
}