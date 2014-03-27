pau
===

parse and understand

- read
- find patterns
- find percentages
- run calculations
- store data results

## Roadmap

- easy to find word occurrence counts...
  - ex: word: 2
- how to get words in their "natural habitat"?
  - ex: one phrase here
  - one word "groups"
  - two word groups
  - multiple word groups
  - what to do with found word groups?
    - what do found word groups tell us?
      1. what words go before other words
      2. what words go after other words
- how to isolate patterns?
  - find POS
    - use guesses for common POS
    - in common POS data set:
      - assign POS to word
      - assign POS weight to word (start at 0.1)
      - then, with later data, if POS is reinforced, add to weight
      - otherwise, weights that are still at 0.1 should probably be pruned at some point
  - ex: one phrase -> number noun
  - how would we know what patterns mean?
  - isolating subject, object, verb (without having previously-learned knowledge to which each POS relates)
  - note: verb/adjective conjugations can possibly be treated as individual words for now...
- how to model pattern data in datastore?
  - ex: number followed by noun
  - how best to retrieve data?
    - pattern search?
    
## Notes

- test data is from:
  - https://vi.wikipedia.org/wiki/Vi%E1%BB%87t_Nam (under Creative Commons License http://creativecommons.org/licenses/by-sa/3.0/deed.vi)
  - http://en.wikipedia.org/wiki/Vietnamese_language (under Creative Commons License http://en.wikipedia.org/wiki/Wikipedia:Text_of_Creative_Commons_Attribution-ShareAlike_3.0_Unported_License)
  
## Setup

- make sure to set encoding in eclipse to UTF-8