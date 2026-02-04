## Aquesta és la documentació de l'activitat 06 Wait i notifyAll()

1. Per què s'atura l'execució al cap d'un temps?
    El motiu per el que l'execució s'atura es per la funció fer reserva. Aquesta en primer lloc és asyncronized, per lo que només un fill hi pot accedir i la resta s'ha d'esperar a que el primer termini. Com que hi han places limitats a reservar y la probabilitat de que un fill canceli és la mateixa un cop que ja no hi han places un fill sense plaça entra a reserva i s'espera fins a que hi hagi plaça. Altre fill que ja té plaça hi pot donarça que vulgui reserva plaça i per tant es produeix un bucle en el que cap dels dos poden surtir, un per que necesita que el que te plaça (darrere seu) cancel·li i l'altre esperant a que el primer reservi per poder "reservar" i que després amb una probabilitat del 50% li toqui cancel·la. 

    Doncs lo mateix que s'ha explicat amb dos fills passa amb molt fills. Un cop que un vol reservar i el que ha reservat no cancel·la perque espera a que un reservi i el que va després seu ja ha anat a cancel·la (pero per exemple no tenia reserva, per tant no ha aparegut una nova i el primer segueix en el wait()) es posa a la cua es forma un tancament del que ningú pot sortir. 

2. Què pasa si es canvia la probabilitat de ferReserva i cancel·laReserva?
    - 70% fer Reserva / 30 cancel·la Reserva:
    
    Tros de codi modificat: 

        '''
        public boolean ferAlgo(double probabilitat) {
            return random.nextDouble() > probabilitat;
        }

        private double chance = 0.3;
        public void run() {
            while (true) {
                if (ferAlgo(chance)) { // si es mes de 70%
                    try {
                        esdeveniment.ferReserva(this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else { // si es menys de 30%
                    esdeveniment.cancelaReserva(this);
                }
                dormir();
            }
        }
        '''
        Resultat: 
        '''
        Assistent-0 ha fet una reserva. Places disponibles: 4
        Assistent-9 ha fet una reserva. Places disponibles: 3       
        Assistent-8 ha fet una reserva. Places disponibles: 2       
        Assistent-6 ha fet una reserva. Places disponibles: 1       
        Assistent-3 no ha pogut cancel·la una reserva inexistent. Places disponibles: 1
        Assistent-5 no ha pogut cancel·la una reserva inexistent. Places disponibles: 1
        Assistent-4 ha fet una reserva. Places disponibles: 0       
        Assistent-1 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-3 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-1 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-0 ha cancel·lat una reserva. Places disponibles: 1
        Assistent-7 ha fet una reserva. Places disponibles: 0       
        Assistent-3 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-0 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
        Assistent-8 ha fet una reserva. Places disponibles: 0  
        '''

    La diferència es que ara faran reserves amb més probabilitat per tant les places que es cancelaran serán molt poques i per tant el flux es trencara més ràpid. Nungú "voldra" cancel·la.

    - 30% fer Reserva / 70% cancel·la

    Tros de codi mofdificat: 

        '''
        public boolean ferAlgo(double probabilitat) {
            return random.nextDouble() > probabilitat;
        }

        private double chance = 0.7;
        public void run() {
            while (true) {
                if (ferAlgo(chance)) { // si es mes de 70%
                    try {
                        esdeveniment.ferReserva(this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else { // si es menys de 30%
                    esdeveniment.cancelaReserva(this);
                }
                dormir();
            }
        }
        '''

        Resultat:
        '''
        Assistent-0 ha fet una reserva. Places disponibles: 4
        Assistent-9 no ha pogut cancel·la una reserva inexistent. Places disponibles: 4
        Assistent-8 ha fet una reserva. Places disponibles: 3
        Assistent-7 ha fet una reserva. Places disponibles: 2
        Assistent-6 no ha pogut cancel·la una reserva inexistent. Places disponibles: 2
        Assistent-5 no ha pogut cancel·la una reserva inexistent. Places disponibles: 2
        Assistent-1 ha fet una reserva. Places disponibles: 1
        Assistent-3 ha fet una reserva. Places disponibles: 0
        Assistent-4 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-8 ha cancel·lat una reserva. Places disponibles: 1
        Assistent-2 ha fet una reserva. Places disponibles: 0
        Assistent-0 ha cancel·lat una reserva. Places disponibles: 1
        Assistent-8 ha fet una reserva. Places disponibles: 0
        Assistent-9 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-3 ha cancel·lat una reserva. Places disponibles: 1
        Assistent-2 ha fet una reserva. Places disponibles: 0
        ...
        Assistent-3 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-7 ha cancel·lat una reserva. Places disponibles: 1
        Assistent-1 ha fet una reserva. Places disponibles: 0
        Assistent-1 ha cancel·lat una reserva. Places disponibles: 1
        Assistent-8 ha fet una reserva. Places disponibles: 0
        Assistent-0 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-6 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-0 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-1 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-0 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-0 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-0 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        Assistent-0 no ha pogut cancel·la una reserva inexistent. Places disponibles: 0
        '''

    En aquest cas, totalment contrari a l'anterior, com que era més probable que les reserves es cancelin que es reservin tarda molt més en aturar-se el flux. 

3. Perque fa falta la llista?

    A la llista es guarda qui ha fet la reserva, si agafariem només una variable simple amb el nombre de reserves qualsevol podria cancel·la una reserva, sense saber si es seva o no. 


