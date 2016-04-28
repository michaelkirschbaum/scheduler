# Availability

Written by Michael Kirschbaum on 4/26/2016

Application to request ZOOM+ schedule and find today's
opening and closing hours for a particular clinic.

To build run from the project root directory:

    'mvn clean install'

To run:

    'java -jar target/scheduler-1.0-SNAPSHOT.jar'

Follow the instruction at the prompt.

Search Parameters
--------------------
Service Line Id: A string which is the ID of a service line. eg. "e675e4e7-978a-4c89-ad09-63b30965821f" for Injury/Illness.

Latitude-Longitude: A string in the format (latitude,longitude). eg. "(45.5235,-122.676)" for downtown Portland.

Page: A number that indicates which page of results to display. The first page is 1, and currently a page consists of
4 clinics.

Duration: A number of minutes that schedule slots are to be long.

Clinic Id: A string which is the ID of a clinic.

Provider Id: A string which is the ID of a provider.

Ordered Lab Id: A string which is the ID of an ordered lab.
