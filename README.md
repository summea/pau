pau
===

parse and understand

_?data mining through is possible it to language a learn_

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
  - is there a way to find _meaning_ of words without being given a plain definition?
    - using context? (neighboring words)
      - context can indicate how words are used... but how can words be used to indicate meaning?
    - machine could make a guess at meaning and update meaning over time as more information is learned
      - but what is _guess_ based on?
      
Generating Meaning: Algorithm A
  1. parse and assign words to dictionary
  2. add previous, current, and next words for each dictionary-assigned word into a list of rules
    - ex: this and that _previous_, _current_, and _next_
  3. repeat process until all words are assigned to dictionary
  4. run through rules list and try to find patterns that can be condensed/abstracted
    - stuck on this step:
      - what rules can we find given the data?
        - so far, we can see places where there are similar surrounding (prev/next) words in the captured lists... but even with this data, we can really only see how often certain patterns are used, right? this is great for pattern statistics... but seems to lead to another road block for uncovering meaning...
  5. update rules list with condensed/updated data ("definitions")
  6. add found "definitions" to words in dictionary

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
    
## Example Output (GMA:A)
    
    dictionary:

    Austroasiatic : 
    Vietnamese : 
    Kinh : 
    second : 
    for : 
    by : 
    spoken : 
    used : 
    people : 
    has : 
    elsewhere : 
    languages : 
    of : 
    given : 
    formerly : 
    family : 
    diacritics : 
    three : 
    official : 
    million : 
    nôm : 
    or : 
    national : 
    far : 
    residing : 
    modified : 
    and : 
    many : 
    that : 
    Latin : 
    native : 
    called : 
    several : 
    alphabet : 
    minorities : 
    language : 
    letters : 
    Việt : 
    characters : 
    ﻿Vietnamese : 
    other : 
    set : 
    about : 
    today : 
    use : 
    most : 
    quốc : 
    from : 
    Chinese : 
    It : 
    Vietnam : 
    The : 
    pronunciation : 
    times : 
    borrowings : 
    tones : 
    ngữ : 
    with : 
    ethnic : 
    is : 
    it : 
    vocabulary : 
    a : 
    as : 
    the : 
    speakers : 
    in : 
    combined : 
    which : 
    additional : 
    tiếng : 
    vernacular : 
    certain : 
    part : 
    first : 
    also : 
    chữ : 

    rules:

    Austroasiatic : [the, language, other, languages]
    Vietnamese : [combined, vocabulary, The, alphabet]
    Kinh : [people, and]
    second : [or, language]
    for : [diacritics, tones]
    by : [has, far]
    spoken : [is, as]
    used : [formerly, a]
    people : [Vietnamese, Kinh]
    has : [it, by, vocabulary, borrowings]
    elsewhere : [residing, It]
    languages : [Austroasiatic, combined]
    of : [part, the, family, which, that, the, set, Chinese]
    given : [nôm, vernacular]
    formerly : [it, used]
    family : [language, of]
    diacritics : [additional, for]
    three : [about, million]
    official : [national, language]
    million : [three, Vietnamese]
    nôm : [chữ, given]
    or : [first, second]
    national : [the, official]
    far : [by, the]
    residing : [Vietnamese, elsewhere]
    modified : [a, set]
    and : [Chinese, it, tones, certain]
    many : [by, ethnic]
    that : [times, of]
    Latin : [a, alphabet]
    native : [the, language]
    called : [characters, chữ]
    several : [speakers, times]
    alphabet : [Vietnamese, quốc, Latin, with]
    minorities : [ethnic, of]
    language : [Austroasiatic, family]
    Việt : [tiếng, is]
    characters : [Chinese, called]
    ﻿Vietnamese : [, tiếng]
    other : [the, Austroasiatic]
    set : [modified, of]
    about : [of, three]
    today : [use, is]
    use : [in, today]
    most : [the, speakers]
    quốc : [alphabet, ngữ]
    from : [borrowings, Chinese]
    Chinese : [from, and, of, characters]
    It : [, is]
    Vietnam : [of, It]
    The : [pronunciation, Vietnamese]
    pronunciation : [vernacular, The]
    times : [several, that]
    borrowings : [has, from]
    tones : [for, and]
    ngữ : [quốc, in]
    with : [alphabet, additional]
    ethnic : [many, minorities]
    is : [It, part, today, a]
    it : [which, has, and, formerly]
    vocabulary : [Vietnamese, has]
    a : [used, modified, is, Latin]
    as : [spoken, a]
    the : [of, Austroasiatic, far, most, of, other]
    speakers : [most, several]
    in : [ngữ, use]
    combined : [languages, Vietnamese]
    which : [of, it]
    additional : [with, diacritics]
    tiếng : [﻿Vietnamese, Việt]
    vernacular : [given, pronunciation]
    certain : [and, letters]
    part : [is, of]
    first : [a, or]
    also : [It, is]
    chữ : [called, nôm]

    
## Notes

- test data is from:
  - https://vi.wikipedia.org/wiki/Vi%E1%BB%87t_Nam (under Creative Commons License http://creativecommons.org/licenses/by-sa/3.0/deed.vi)
  - http://en.wikipedia.org/wiki/Vietnamese_language (under Creative Commons License http://en.wikipedia.org/wiki/Wikipedia:Text_of_Creative_Commons_Attribution-ShareAlike_3.0_Unported_License)
  
## Setup

- make sure to set encoding in eclipse to UTF-8