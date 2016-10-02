Dopo aver chiuso hadoop tutto, fare:
-ssh localhost

Andare nella directory di hadoop-2.7.0 e fare:
bin/hdfs namenode -format
sbin/start-dfs.sh
bin/hdfs dfs -mkdir /user
bin/hdfs dfs -mkdir /user/inputs


bin/hdfs dfs -put PATH_to/Dati_Csv /user/inputs

(sostituire il primo argomento con la location del jar)
(sostituire ultimo argomento con il path al proprio hadoop. ATTENZIONE: se la cartella è hadoop-2.7.0, cmq il path deve finire con hadoop-2.7.0/etc/hadoop)
(quando lanci il jar, la cartella provahadoop4_lib deve essere nella stessa directory del jar)

bin/hadoop jar /Users/daniele/git/CityBikeService/CityBike/provahadoop3.jar hdfs://localhost:9000/user/inputs/Dati_Csv/ hdfs://localhost:9000/user/output1 hdfs://localhost:9000/user/output2 hdfs://localhost:9000/user/output3 /Users/daniele/Desktop/hadoop-2.7.0/etc/hadoop