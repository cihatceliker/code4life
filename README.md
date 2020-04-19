# [code4life](www.codingame.com/ide/puzzle/code4life)

Cihat Emre Çeliker - 20160808028

username: cihatemreceliker

## Strategy:


- First, we recursively loop through the samples to find the best one.
    - If we are currently carrying any sample, that will be the best one, because if it wasn't good enough, we wouldn't take it in the first place.
    - If we are not carrying, we simply look to healths that samples will give after research, and found the most health-giver as the best one. Of course, we only look at the ones that are not carried by the other player.
        - After this, if we found a sample that is not carried by us, we go to the "DIAGNOSIS" module and connect to it.
- After finding the best sample, and knowing that we are carrying it, we check the molecules. Comparing our storages with the costs of the molecules of this sample.
    - If we have enough molecules:
        - If we are already at the "LABORATORY", we research this sample.
        - Otherwise, we go to the "LABORATORY". We will research it on the next turn.
    - If we don't have enough molecules:
        - If we are already at the "MOLECULES" module, we download the needed ones.
        - Otherwise, we go to the "MOLECULES" module.

