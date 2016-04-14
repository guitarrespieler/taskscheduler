# taskscheduler
Java implementation of a multi-level task scheduler,which is alike a usual OS uses.

English description (Hungarian description below)
-----------------------------------------------------------------------

The scheduler handles tasks with static priority(their priority don't change dinamically).
The priority is between 0 - 9 where 0 is the highest priority.

	1st level(priority: 0-4): non-preemptive SJF scheduler

	2nd level(priority: 5-9): preemptive RR scheduler, timeslice: 2

Input:

	One task's data per line (max. 10 tasks alltogether). A line should be like:
	Letter of the task ( A,B,C...)

	priority of the task(0-9)

	the point of time when the task arrives(integer >= 0), 
	the task will be able to run in the next timeslice
		(if it arrives at 5s, it can start at 6s)

	CPU-burst of the task(integer >= 1)

	For example:

		A,3,0,5

		B,4,0,4

		C,6,5,8

		D,6,7,8

There should be an empty line after the last task - it means that the scheduler can be started.

After that line the app writes out the result.

Output:

	First line: running order of the given tasks.

	Second line: waiting time of every task, and the tasks ordered by their end-time:

			[First task's letter]:[waiting time],[second task's letter]:[waiting time]...

	An output example(this is the answer to the previous input):

		BACDCDCDCD

		B:0,A:4,C:10,D:10

Hungarian description
-------------------------------------------------------------------------------------------------

HF1. Többszintű ütemező megvalósítása

Határidő: 2016. április 15. 

Becsült programozási idő: 1-3 óra


Készítsen egy programot, amely statikus többszintű ütemező működését szimulálja!

Az ütemező 0 és 9 közötti statikus prioritású (0 a legmagasabb)
taszkokat kezel az alábbi szinteken és algoritmusokkal:

	1. szint (0-4 közötti prioritás) nem preemptív, SJF ütemező

		(a prioritás a szinten belül már nem játszik szerepet,
		"vegytiszta" SJF ütemezési algoritmus működik)

	2. szint (5-9) preemptív RR ütemező, időszelet: 2

		(a prioritás a szinten belül itt sem játszik szerepet,
		"vegytiszta" RR ütemező fut)

Bemenet (standard input, stdin)

	Soronként egy (max. 10) taszk adatai. Egy sor felépítése (vesszővel elválasztva):

		a taszk betűjele (A, B, C...) megfelel az érkezési sorrendnek

		a taszk prioritása (0-9)

		a taszk indítási ideje (egész szám >= 0), a következő időszeletben már futhat
		(0: az ütemező indításakor már létezik)

		a taszk CPU-löketideje (egész szám >= 1)

Példa:

	A,3,0,5

	B,4,0,4

	C,6,5,8

	D,6,7,8

A bemenet végét egy üres sor (utána EOF) jelzi. Ekkor kell a kimenetre kiírni az eredményt.

Kimenet (standard output, stdout)

	A kimenet első sorában a taszkok futási sorrendje betűjeleikkel (csak betűk, szóközök nélkül).

	A második sorban a teljes várakozási idő taszkonként, a befejezésük sorrendjében 
	az alábbi formában (vesszővel elválasztva, szóközök nélkül):

		1. taszk betűjel:várakozási idő,2. betűjel:várakozási idő, ...

	Példa (a fenti bemenetre adott válasz):

			BACDCDCDCD

			B:0,A:4,C:10,D:10

Értékelés

	Az ütemező helyes működése: 2 pont, a  várakozásiidők jó számítása: 1 pont (összesen 3 pont).

Technikai információk

	A programot Java nyelven kell elkészíteni, és a HF portálon kell leadni a megadott határidőig.

	A beküldött Java programnak tartalmaznia kell egy "Main" nevű osztályt, melynek
	része a feladatot megoldó "main" függvény. A program tetszőleges számú forrásfájlból állhat.
	A program nem használhat a standard inputon és outputon kívül semmilyen más erőforrást,
	így nem végezhet fájlműveleteket és nem nyithat hálózati kapcsolatokat.
