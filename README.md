# MochiCaps
MochiCaps is a Twitter (and soon other social media too!) bot that is built to post anime screencaps/frames automatically, but will be able to do more (also soon!)

Originally built in Python as [tweet-screencap or KeionScreencaps.py](https://github.com/helpimnotdrowning/tweet-screencap), I decided that it was becoming unreadable and almost uneditable, the only way to fix it was to rewrite it completely.

You can see the "main" bot at [@keionscreencaps on Twitter](https://twitter.com/keionscreencaps) (using `tweet-screencap`, NOT `MochiCaps` as of now). It may go down frequently, but that's due to my sometimes-laziness at "refilling" the frames and probably Windows Terminal crashing, **not** due to instability.

## Using
coming soon :)

## Building
#### Warning: this entire project is built and tested on Windows 11 using Java 17 with the Adoptium JDK. Linux and macOS users might (but probably won't) run into some problems; in case you do, feel free to raise an issue! If you mention Termux or Android in your issue I will launch you into the stratosphere.
If you have my [lazy-scripts](https://github.com/helpimnotdrowning/lazy-scripts) installed (meaning there's an entry in your `PATH` to the lazy-scripts folder), you can simply run:
```pwsh
PS C:\...> gitgradlec https://github.com/helpimnotdrowning/MochiCaps
```
The folder with the resulting JAR will automatically open once finished.

### Otherwise, you can:
1) Clone the repo:
```pwsh
PS C:\...> git clone https://github.com/helpimnotdrowning/MochiCaps
```

2) `cd` into it:
```pwsh
PS C:\...> cd MochiCaps
```

3) Build with Gradle:
```pwsh
PS C:\...\MochiCaps> gradlew build
```

The resulting JAR will be in your `MochiCaps\build\libs` folder.

