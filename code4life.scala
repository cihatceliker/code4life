import math._
import scala.util._
import scala.io.StdIn._

object Player extends App {
    val projectCount = readLine.toInt
    for(i <- 0 until projectCount) {
        val Array(a, b, c, d, e) = (readLine split " ").map (_.toInt)
    }

    // game loop
    while(true) {
        // this input will be the first player. its our player.
        decide(readLine split " ")
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

    // it checks whether we need any molecule or not
    def findMolecule(best_sample : Array[String], count : Int, player : Array[String]) : Unit = {
        // this will be true only if we are not carrying the best found sample.
        if (best_sample(0) == "done") {
            return
        }
        // count equals 5 means that we have all the necessary molecules for the sample we are carrying.
        if (count == 5) {
            goOrConnect("LABORATORY", best_sample(0), player(0))
            return
        }
        // it looks if we need more molecule or not. checks our storage.
        if (best_sample(5+count).toInt >= player(3+count).toInt) {
            goOrConnect("MOLECULES", "ABCDE".substring(count, count+1), player(0))
            return
        }
        return findMolecule(best_sample, count+1, player)
    }

    // it simply loops through the samples recursively until it finds the best one.
    // if we are not carrying that best sample. we will go diagnosis and download it.
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

    // if we're already carrying one of the samples, we'll return it as the bigger one.
    // so we'll just find and take the best samples only if we are not carrying any.
    // bigger sample means bigger health after research. 
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

    // if we are already at the module, we just connect to sample. otherwise we go to module. 
    // here, sample can be sample id or a molecule. target is our current position.
    def goOrConnect(module : String, sample : String, target : String) {
        if (module == target) {
            println("CONNECT " + sample)
        }
        else {
            println("GOTO " + module)
        }
    }
}