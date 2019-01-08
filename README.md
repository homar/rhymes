Brief Description:
Solution uses Trie data structure to implement suffix tree. This way it is quite fast to find a right path
while looking for a rhyme. The hardest, and the slowest, part is to find all matching words. 
Some randomisation could be used here, for example to ignore some subtrees but that could easily lead to uneven chances
for different matching words to be selected. 
From task description I understand that this is for siri, so it doesn't need to be case sensitive.
TrieNode and Trie classes are not public because they are just implementation details and should not be used outside.
When there is no matching word an exception is thrown. This was not stated in a problem description. Other options are possible here.

Usage:
I implemented very simple command line client. It asks for a path to a file with words that will be stored in dictionary. 
Then it waits for a word and returns a rhyme for it.
It assumes each line in a file contains a single word. If this is not true the program may not work correctly(and probably will not).
To run the application"
- unzip
- navigate into rhymes folder
- type `./gradlew run`
- follow displayed command and information in this README.md ;)

Assumptions:
- my trie structure uses Map for readability, char[] would have better performance 
- world doesn't rhyme with itself
- the code doesn't need to be threadsafe, it is possible to make it threadsafe but it would be slower and more complex
- if the dictionary is huge and there are many possible words that matches input it may cause memory issue to collect them all. If this was a real application I'd consider some randomisation while scanning for all the possible words(so on another level than it is currently implemented)


Complexity:
O(s + m*logn) where s is the lenght of the input word, m is the average lenght of items in the dictionary and n is the number of items in the dictionary.

