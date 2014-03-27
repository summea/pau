pau
===

parse and understand

#### _?data mining through is possible it to language a learn_

## Goals

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
    - but what is an anchor we can use to base our guesses around?
      - trying to just use data with no previously-given definitions
    - how can we determine meaning of abstract words without definitions?
      - would enough data help isolate meanings (via neighboring words?)
  - ex: one phrase -> number noun
  - how would we know what patterns mean?
  - isolating subject, object, verb (without having previously-learned knowledge to which each POS relates)
  - note: verb/adjective conjugations can possibly be treated as individual words for now...
- how to model pattern data in datastore?
  - ex: number followed by noun
  - how best to retrieve data?
    - pattern search?
- meaning from abstract words?
  - is there a way to find meaning of words without being given a plain definition?
    - using context? (neighboring words)
    

## Example Output

    en data:

    find single word occurances:
    Vietnamese : 2
    Kinh : 1
    second : 1
    about : 1
    by : 1
    spoken : 1
    It : 2
    people : 1
    Vietnam : 2
    elsewhere : 1
    of : 4
    three : 1
    official : 1
    ethnic : 1
    is : 3
    million : 1
    or : 1
    national : 1
    a : 1
    as : 1
    residing : 1
    the : 2
    and : 1
    many : 1
    native : 1
    tiếng : 1
    minorities : 1
    language : 3
    first : 1
    Việt : 1
    also : 1﻿
    Vietnamese : 1
    


    possible common POS and/or subjects:
    of
    is
    language

    adding value... key:of weight:{weight=0.1}
    adding value... key:is weight:{weight=0.1}
    adding value... key:language weight:{weight=0.1}

    find neighbor word occurances:
    is the : 2
    of Vietnam : 2
    language of : 2


    possible data topics:
    is the
    of Vietnam
    language of

    find single word occurances:
    Austroasiatic : 2
    Vietnamese : 2
    for : 1
    by : 1
    used : 1
    has : 2
    languages : 1
    of : 4
    formerly : 1
    given : 1
    family : 1
    diacritics : 1
    nôm : 1
    far : 1
    and : 2
    modified : 1
    that : 1
    Latin : 1
    called : 1
    several : 1
    alphabet : 2
    language : 1
    letters : 1
    characters : 1
    other : 1
    set : 1
    most : 1
    use : 1
    today : 1
    from : 1
    quốc : 1
    Chinese : 2
    It : 1
    The : 1
    pronunciation : 1
    times : 1
    borrowings : 1
    tones : 1
    ngữ : 1
    is : 2
    with : 1
    it : 2
    vocabulary : 1
    a : 2
    the : 3
    speakers : 1
    in : 1
    combined : 1
    which : 1
    additional : 1
    certain : 1
    vernacular : 1
    part : 1
    chữ : 1


    possible common POS and/or subjects:
    of
    the

    adding value... key:of weight:{weight=0.2}
    adding value... key:the weight:{weight=0.1}

    find neighbor word occurances:
    of the : 2


    possible data topics:
    of the

## Notes

- test data is from:
  - https://vi.wikipedia.org/wiki/Vi%E1%BB%87t_Nam (under Creative Commons License http://creativecommons.org/licenses/by-sa/3.0/deed.vi)
  - http://en.wikipedia.org/wiki/Vietnamese_language (under Creative Commons License http://en.wikipedia.org/wiki/Wikipedia:Text_of_Creative_Commons_Attribution-ShareAlike_3.0_Unported_License)
  
## Setup

- make sure to set encoding in eclipse to UTF-8