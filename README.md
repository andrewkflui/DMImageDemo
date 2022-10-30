# Digital Image Interactive Demo 

A set of Java applications each of which an interactive demonstration application on digital image properties or processing

Developed in 2006 to 2009

Copyright (C) 2009 - Andrew Kwok-Fai Lui

The Open University of Hong Kong

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, see http://www.gnu.org/licenses/.

## Introduction

There are 4 interactive demo applications included in this package

1. Image Processing Demo Platform (`faifai.image.SimpleImageProcessor`)
2. Rotation and Hole Demo (`faifai.image.RotationHoleDemo`)
3. Aliasing and Low Pass Demo (`faifai.image.LowPassDemo`)
4. JPEG File Quality Demo (`faifai.image.JPEGFileQualityDemo`)

## Running the Applications

### Pre-requisites

1. Java JRE 1.8 or above

### Running the Applications Through the Launcher

A launcher application `ApplicationLauncher.class` is provided for running each of the 4 applications.

1. Download the repository to a folder, assuming that it is `/app/DMImageDemo`. The Java classes are found in the `bin` folder.
2. Execute `ApplicationLauncher.class` insider the folder

> `cd /app/DMImageDemo`
> 
> `java -cp "./bin" ApplicationLauncher`

### Running Individual Application

Alternatively, each of the 5 applications can be executed from their main classes (as in the above list).

> `cd /app/DMImageDemo`
> 
> `java -cp "./bin" faifai.image.SimpleImageProcessor`

## Overview of the Applications

### Image Processing Demo Platform 
Class: `faifai.image.SimpleImageProcessor`
* Provide loading of sample images or custom image files
* Support image processing operations such as warm, chill, roughen, rotation, posterize, greyscale
<img width="1129" alt="Screenshot 2022-10-31 at 2 32 25 AM" src="https://user-images.githubusercontent.com/8808539/198896146-cf122989-18b9-43d1-9a82-595f31ad4183.png">

### Rotation and Hole Demo 
Class: `faifai.image.RotationHoleDemo`
* Demo the difference between forward mapping and backward mapping.
* Show the __hole__ effect
* Demo supersampling methods
<img width="800" alt="Screenshot 2022-10-31 at 2 29 34 AM" src="https://user-images.githubusercontent.com/8808539/198896155-0f045721-6345-45d9-b920-d6d144e8b889.png">

### Aliasing and Low Pass Demo 
Class: `faifai.image.LowPassDemo`
* Demo the removal of high frequency signal from images with a low-pass filter.
<img width="520" alt="Screenshot 2022-10-31 at 2 29 52 AM" src="https://user-images.githubusercontent.com/8808539/198896151-d4954932-c241-4d78-a7d7-7c931ad4a324.png">

### JPEG File Quality Demo 
Class: `faifai.image.JPEGFileQualityDemo`
* Demo the file size reduction and the quality of JPEG images.
<img width="800" alt="Screenshot 2022-10-31 at 2 31 27 AM" src="https://user-images.githubusercontent.com/8808539/198896130-91ec562a-9c64-44ce-adc4-343b515ad70a.png">
