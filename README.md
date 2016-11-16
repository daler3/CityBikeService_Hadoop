# CityBikeService (Hadoop project)

Used Hadoop in a fully distributed cluster to infer qualitative data regarding the use of the CityBike Service in a city (MapReduce, Java).

Datasets used can be download from this url: 
[Download datasets](http://home.deib.polimi.it/guinea/Materiale/Middleware/index.html)

Explanation of the source code of the project and explanation on how to set-up a fully distributed cluster on Hadoop can be found here: 
[Download doc](https://github.com/daler3/CityBikeService_Hadoop/blob/master/Doc-CityBikeService_Hadoop.pdf)

Source code of the main program can be found here: 
[Source code](https://github.com/daler3/CityBikeService_Hadoop/tree/master/code%26libraries%26deliverables/Source_Code_CityBikeHadoop/CityBike_Comp/src/primo)

You must provide by command line the input & output path in command line. Examples (to be edited according to your own paths) can be found here:
[Examples of input & output - command line](https://github.com/daler3/CityBikeService_Hadoop/blob/master/code%26libraries%26deliverables/CityBike_Arguments.txt)

You can also find jar files & libraries needed (for each jar) here: 
[Jar & libraries](https://github.com/daler3/CityBikeService_Hadoop/tree/master/code%26libraries%26deliverables)


The goal of this project is to infer qualitative data regarding the use of the CitiBike Service in New York City. Start by downloading and combining the data sets collected during 2015 from this url.

Each row in the data set contains the following data:

    tripduration
    starttime
    stoptime
    start station id
    start station name
    start station latitude
    start station longitude
    end station id
    end station name
    end station latitude
    end station longitude
    bikeid
    usertype (customer = 24-hour or 7-day pass; subscriber = annual member)
    birth year
    sex of the biker 


Information to provide using Hadoop: 

    - average duration of trips per week in 2015
    - number of customers (NOT subscribers) using the bikes per week in 2015
    - number of trips and average duration of trips per biker age range (16-19, 20-29, 30-39, 40-49, 50-59, 60-69)
    - for each day between the 1st of June and the 31st of August provide the id and name of the station that saw the most    amount of traffic (number of incoming bikes + number of outgoing bikes) 
