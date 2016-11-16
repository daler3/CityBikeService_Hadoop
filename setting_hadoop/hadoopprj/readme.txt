If Hadoop is closed, type:
- ssh localhost

Go to hadoop-2.7.0 directory and type:
bin/hdfs namenode -format
sbin/start-dfs.sh
bin/hdfs dfs -mkdir /user
bin/hdfs dfs -mkdir /user/inputs


bin/hdfs dfs -put PATH_to/Dati_Csv /user/inputs

(replace the first argument with the location of your Jar)
(replace the last argument with the location of your input. ATTENTION: if your folder is named hadoop-2.7.0, the path must end anyway with: hadoop-2.7.0/etc/hadoop)
(when you launch the jar, the folder provahadoop4_lib must be in the same directory of the jar file)

Example for launching (with 'daniele' as a username):
bin/hadoop jar /Users/daniele/git/CityBikeService/CityBike/provahadoop3.jar hdfs://localhost:9000/user/inputs/Dati_Csv/ hdfs://localhost:9000/user/output1 hdfs://localhost:9000/user/output2 hdfs://localhost:9000/user/output3 /Users/daniele/Desktop/hadoop-2.7.0/etc/hadoop
