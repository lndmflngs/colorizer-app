# colorizer-app

[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/license-Apache%202-red.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://img.shields.io/badge/Download-v.0.1.4-blue.svg)][1]

<img src="https://github.com/lndmflngs/colorizer-app/blob/master/graphic/2.png?raw=true" width="45%" />

## What?
The app use the Colorful Image Colorization algorithm that takes in a black & white photos and returns the colorized version of it. The algorithm uses deep learning to classify objects/regions within the image and color them accordingly

## Setup
* Fetch you [Algorithmia][2] api key
* Add your key as a string resource with name "algorithmia_api_key" to [init algo client][3]
* Add your [Hosted Data Source][5]
* Change [HOSTED_DATA_PATH][4] value with you path

## Support
If you want to support the the project or show your appreciation feel free to **[buy me a coffee / donate](https://www.paypal.me/kekc1304/1)**

## Issue Tracking
Found a bug? Have an idea for an improvement? Feel free to [add an issue](../../issues)

## Mention
Icon made by [Freepik][6] from [flaticon][7] is licensed by [CC 3.0 BY][8]

## License

```
Copyright (C) 2019 Ivan Zinovyev (https://github.com/lndmflngs)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
[1]: https://github.com/lndmflngs/colorizer-app/releases/latest
[2]: https://algorithmia.com/
[3]: https://github.com/lndmflngs/colorizer-app/blob/master/app/src/main/java/com/lndmflngs/colorizer/ui/fragments/ResultFragment.kt
[4]: https://github.com/lndmflngs/colorizer-app/blob/master/app/src/main/java/com/lndmflngs/colorizer/algorithmia/AlgoClient.kt
[5]: https://algorithmia.com/developers/data/hosted/
[6]: https://www.freepik.com/
[7]: https://www.flaticon.com/
[8]: http://creativecommons.org/licenses/by/3.0
