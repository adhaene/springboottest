package com.bezkoder.spring.jpa.h2.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Java Regex Summary
Package: java.util.regex
Main Classes:
Pattern — Compiles a regex into a pattern.
Matcher — Matches the pattern against input text.
Common Methods:
Pattern.compile(String regex)
matcher(String input)
matches() — Checks if the entire string matches.
find() — Finds the next subsequence match.
group() — Returns the matched substring.
Flags: Pattern.CASE_INSENSITIVE, Pattern.MULTILINE, etc.
Syntax Examples:
\\d → Digit
\\w → Word character
\\s → Whitespace
. → Any character
+, *, ? → Quantifiers
[abc] → Character set
(abc) → Capturing group

Key Points:

Always escape backslashes in Java strings (e.g., \\d instead of \d).
Use find() for partial matches, matches() for full-string matches.
Wrap regex compilation in try-catch for safety.

Pattern Meaning Example Match
.   Any single character except newline a.c → matches abc, axc
^   Start of string ^Hello → matches "Hello world"
$   End of string   world$ → matches "Hello world"
*   0 or more repetitions   go* → g, go, goo
+   1 or more repetitions   go+ → go, goo
?   0 or 1 repetition (optional)    colou?r → color, colour
{n} Exactly n repetitions   \d{3} → 123
{n,}    n or more repetitions   \d{2,} → 12, 1234
{n,m}   Between n and m repetitions \d{2,4} → 12, 123, 1234
[]  Character set   [aeiou] → matches vowels
[^] Negated set [^0-9] → non-digit
\d  Digit (0–9) \d\d → 42
\D  Non-digit   \D+ → abc
\w  Word char (letters, digits, _)  \w+ → hello_123
\W  Non-word char   \W+ → !@#
\s  Whitespace  \s+ → spaces, tabs
\S  Non-whitespace  \S+ → word
|   OR  a|b → a or b
()  Group   (ab)+ → ab, abab
(?: )   Non-capturing group (?:ab)+ → same as above but no capture
(?=...) Positive lookahead  \d(?=px) → digit before px
(?!...) Negative lookahead  \d(?!px) → digit not before px

 */
public class RegexExample {
    public static void main(String[] args) {
        String text = "Contact us at support@example.com or sales@example.org";

        // Regex to match email addresses
        String regex = "\\b[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}\\b";

        try {
            // Compile the regex pattern
            Pattern pattern = Pattern.compile(regex);

            // Create matcher for the input text
            Matcher matcher = pattern.matcher(text);

            System.out.println("Found email addresses:");
            boolean found = false;

            // Find all matches
            while (matcher.find()) {
                System.out.println(" - " + matcher.group());
                found = true;
            }

            if (!found) {
                System.out.println("No matches found.");
            }
        } catch (Exception e) {
            System.err.println("Invalid regex: " + e.getMessage());
        }
    }
}
