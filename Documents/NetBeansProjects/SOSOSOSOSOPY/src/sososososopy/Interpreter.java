/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sososososopy;

/**
 *
 * @author komp
 */
public class Interpreter {
private int R1 = 0, R2 = 0, R3 = 0, R4 = 0, ProcesID = 0;
int licznik;

	public Interpreter( ) 
        {
		
	}

	public void set_R1(int a) {
		this.R1 = a;
	}

	public void set_R2(int b) 
        {
		this.R2 = b;
	}
	public void set_R3(int C) {
		this.R3 = C;
	}
        public void set_R4(int a) {
		this.R1 = a;
	}
	public int get_R1() {
		return R1;
	}

	public int get_R2() {
		return R2;
	}

	public int get_R3() {
		return R3;
	}
        public int get_R4() {
		return R4;
	}

	public void procesRun()
        {}
        public void sprawdzRozkaz()
        {}
        int x=0;
        public void increase_counters(){ x=x+1;}

        void setstate()
        {}
        void push_writer (String procespiszący, String komunikat,String procesczytający){}
    String readFile(String fileName,int flag){return null;
}
	public Boolean program(String rozkaz[],int ProcesID) 
        {
		this.done = false;
		this.working = false;
		this.fail = false;
		
		// Rozkazy arytmetyczne
		if (rozkaz[0].equals("AD")) {
			if (rozkaz[1].equals("A")) {
				if (rozkaz[2].equals("B")) {
					R1 += get_R2();
                                        increase_counters();
				} else if (rozkaz[2].equals("C")) {
					R1+= get_R3();
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R1+= get_R4();
                                        increase_counters();
				}
			} else if (rozkaz[1].equals("B")) {
				if (rozkaz[2].equals("A")) {
					R2 += get_R1();
                                        increase_counters();
				} else if (rozkaz[2].equals("C")) {
					R2 += get_R3();
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R2+= get_R4();
                                        increase_counters();
				}
			} else if (rozkaz[1].equals("C")) {
				if (rozkaz[2].equals("A")) {
					R3 += get_R1();
                                        increase_counters();
				} else if (rozkaz[2].equals("B")) {
					R3 += get_R2();
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R3+= get_R4();
                                        increase_counters();
				}
			} else {
				if (rozkaz[2].equals("A")) {
					R4 += get_R1();
                                        increase_counters();
				} else if (rozkaz[2].equals("B")) {
					R4+= get_R2();
                                        increase_counters();
				}else if (rozkaz[2].equals("C")) {
					R4+= get_R3();
                                        increase_counters();
				}
			}

			this.working = false;
			return true;

		} else if (rozkaz[0].equals("SB")) {
			if (rozkaz[1].equals("A")) {
				if (rozkaz[2].equals("B")) {
					R1 -= get_R2();
                                        increase_counters();
				} else if (rozkaz[2].equals("C")) {
					R1-= get_R3();
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R1-= get_R4();
                                        increase_counters();
				}
			} else if (rozkaz[1].equals("B")) {
				if (rozkaz[2].equals("A")) {
					R2 -= get_R1();
                                        increase_counters();
				} else if (rozkaz[2].equals("C")) {
					R2 -= get_R3();
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R2 -= get_R4();
                                        increase_counters();
				}
			} else if (rozkaz[1].equals("C")) {
				if (rozkaz[2].equals("A")) {
					R3 -= get_R1();
                                        increase_counters();
				} else if (rozkaz[2].equals("B")) {
					R3 -= get_R2();
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R3 -= get_R4();
                                        increase_counters();
				}
			} else {
				if (rozkaz[2].equals("A")) {
					R4 -= get_R1();
                                        increase_counters();
				} else if (rozkaz[2].equals("B")) {
					R4-= get_R2();
                                        increase_counters();
				}else if (rozkaz[2].equals("C")) {
					R4-= get_R3();
                                        increase_counters();
				}
			}
                

			this.working = false;
			return true;
                }else if (rozkaz[0].equals("AX")) {
			if (rozkaz[1].equals("A")) {
				R1+=Integer.parseInt(rozkaz[2]);
                                increase_counters();
			} else if (rozkaz[1].equals("B")) {
				R2+=Integer.parseInt(rozkaz[2]);
                                increase_counters();
			} else if (rozkaz[1].equals("C")) {
				R3+=Integer.parseInt(rozkaz[2]);
                                increase_counters();
			} else {
				R4+=Integer.parseInt(rozkaz[2]);
			increase_counters();
                        }

			this.working = false;
			return true;

		} else if (rozkaz[0].equals("SB")) {
			if (rozkaz[1].equals("A")) {
				R1-=Integer.parseInt(rozkaz[2]);
			increase_counters();
                        } else if (rozkaz[1].equals("B")) {
				R2-=Integer.parseInt(rozkaz[2]);
			increase_counters();
                        } else if (rozkaz[1].equals("C")) {
				R3-=Integer.parseInt(rozkaz[2]);
			increase_counters();
                        } else {
				R4-=Integer.parseInt(rozkaz[2]);
			increase_counters();
                        }
                        this.working = false;
			return true;
		} else if (rozkaz[0].equals("IN")) {
			if (rozkaz[1].equals("A")) {
				R1+= 1;
			increase_counters();
                        } else if (rozkaz[1].equals("B")) {
				R2+= 1;
			increase_counters();
                        } else if (rozkaz[1].equals("C")) {
				R3+= 1;
			increase_counters();
                        } else {
				R4 += 1;
			increase_counters();
                        }

			this.working = false;
			return true;

		} else if (rozkaz[0].equals("DC")) {
			if (rozkaz[1].equals("A")) {
				R1-= 1;
			increase_counters();
                        } else if (rozkaz[1].equals("B")) {
				R2-= 1;
			increase_counters();
                        } else if (rozkaz[1].equals("C")) {
				R3-= 1;
			increase_counters();
                        } else {
				R4 -= 1;
			increase_counters();
                        }


			this.working = false;
			return true;
		
		} else if (rozkaz[0].equals("MU")) {
			if (rozkaz[1].equals("A")) {
				if (rozkaz[2].equals("B")) {
					R1 *= get_R2();
                                        increase_counters();
				} else if (rozkaz[2].equals("C")) {
					R1*= get_R3();
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R1*= get_R4();
                                        increase_counters();
				}
			} else if (rozkaz[1].equals("B")) {
				if (rozkaz[2].equals("A")) {
					R2 *= get_R1();
                                        increase_counters();
				} else if (rozkaz[2].equals("C")) {
					R2 *= get_R3();
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R2*= get_R4();
                                        increase_counters();
				}
			} else if (rozkaz[1].equals("C")) {
				if (rozkaz[2].equals("A")) {
					R3 *= get_R1();
                                        increase_counters();
				} else if (rozkaz[2].equals("B")) {
					R3 *= get_R2();
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R3*= get_R4();
                                        increase_counters();
				}
			} else {
				if (rozkaz[2].equals("A")) {
					R4 *= get_R1();
                                        increase_counters();
				} else if (rozkaz[2].equals("B")) {
					R4*= get_R2();
                                        increase_counters();
				}else if (rozkaz[2].equals("C")) {
					R4*= get_R3();
                                        increase_counters();
				}
			}


			this.working = false;
			return true;
		} else if (rozkaz[0].equals("LO")) {
			if (rozkaz[1].equals("A")) {
				R1 = Integer.parseInt(rozkaz[2]);
                                increase_counters();
			} else if (rozkaz[1].equals("B")) {
				R2 = Integer.parseInt(rozkaz[2]);
                                increase_counters();
			} else if (rozkaz[1].equals("C")){
				R3 = Integer.parseInt(rozkaz[2]);
                                increase_counters();
			} else {
				R4 = Integer.parseInt(rozkaz[2]);
                                increase_counters();
			}
                        

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("MX")) {
			if (rozkaz[1].equals("A")) {
				R1*=Integer.parseInt(rozkaz[2]);
                                increase_counters();
			} else if (rozkaz[1].equals("B")) {
				R2*=Integer.parseInt(rozkaz[2]);
                                increase_counters();
			} else if (rozkaz[1].equals("C")) {
				R3*=Integer.parseInt(rozkaz[2]);
                                increase_counters();
			} else {
				R4*=Integer.parseInt(rozkaz[2]);
                                increase_counters();
			}


				this.working = false;
				return true;
			
			
		} else if (rozkaz[0].equals("MV")) {
			if (rozkaz[1].equals("A")) {
				if (rozkaz[2].equals("B")) {
					R1 =R2;
                                        increase_counters();
				} else if (rozkaz[2].equals("C")) {
					R1=R3;
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R1=R4;
                                        increase_counters();
				}
			} else if (rozkaz[1].equals("B")) {
				if (rozkaz[2].equals("A")) {
					R2 =R1;
                                        increase_counters();
				} else if (rozkaz[2].equals("C")) {
					R2 =R3;
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R2=R4; 
                                        increase_counters();
				}
			} else if (rozkaz[1].equals("C")) {
				if (rozkaz[2].equals("A")) {
					R3 =R1;
                                        increase_counters();
				} else if (rozkaz[2].equals("B")) {
					R3 =R2;
                                        increase_counters();
				}else if (rozkaz[2].equals("D")) {
					R3 =R4;
                                        increase_counters();
				}
			} else {
				if (rozkaz[2].equals("A")) {
					R4 =R1;
                                        increase_counters();
				} else if (rozkaz[2].equals("B")) {
					R4 =R2;
                                        increase_counters();
				}else if (rozkaz[2].equals("C")) {
					R4 =R3;
                                        increase_counters();
				}
			}
			this.working = false;
			return true;
		}

		
			

	
		
		 if (rozkaz[0].equals("JP")) {
			licznik = Integer.parseInt(rozkaz[1]);
			this.working = false;
			return true;
		} else if (rozkaz[0].equals("JPT")) {
			if (this.flag_F == true) {
				licznik = Integer.parseInt(rozkaz[1]);
			}
			this.working = false;
			return true;
		} else if (rozkaz[0].equals("JPF")) {
			if (this.flag_F == false) {
				licznik = Integer.parseInt(rozkaz[1]);
			}
			this.working = false;
			return true;
		}
                
            switch (rozkaz[0]) 
            {
                case "MF":
                    Disc.tworzeniaPliku(rozkaz[1], rozkaz[2]);
                    this.working = false;
                    return true;
                case "WF":
                    Disc.saveOnDisc(rozkaz[1],rozkaz[2],rozkaz[3]);
                    this.working = false;
                    return true;
                /*case "FRD":
                    disk.wyswietlDanyPlik(rozkaz[1], rozkaz[2]);
                    this.working = false;
                    return true;*/
                case "DF":
                    Disc.removeFile(rozkaz[1]);
                    this.working = false;
                    return true;
                case "EF":
                    Disc.zmianaNazwy(rozkaz[1],rozkaz[2],rozkaz[3],rozkaz[4]);
                    this.working = false;
                    return true;
                
                 case "CP":
		   new new_process(rozkaz[1],rozkaz[2],rozkaz[3]);
                    this.working = false;
                    return true;
                case "XS":
		    Wiadomosc wiadomosc = new Wiadomosc(PID, rozkaz[2]); //manager.getMain().pcb.PID
                    IPC.wyslij(wiadomosc,Integer.parseInt(rozkaz[1]));
                    this.working = false;
                    return true;
                case "XDB":
                    if(IPC.usunSkrzynkeNr(Integer.parseInt(rozkaz[1]),PID))
                    this.working = false;
                    return true;
		/*case "XP":
                    semafor.P();
                    this.working = false;
                    return true;
                case "XV":
                    semafor.V();
		    this.working = false;
                    return true;*/
                default:
                    return false;
            }
        }
String rozkaz[];
	private Boolean done = false, working = false, fail = false, flag_F = false;
   void interpretacja(){
String s = null;
String x = null;
int i=0;
StringBuilder a= new StringBuilder();
do{
    
    rozkaz=procesmanagment.readMemory(licznik);
    x=readMemory(licznik+1);
    licznik++;
}while(x!="#");
   program(rozkaz,ProcesID);}	
	public void showRegisters() {
		System.out.println("Rejestry:");
		System.out.println("A: " + get_R1());
		System.out.println("B: " + get_R2());
		System.out.println("C: " + get_R3());
                System.out.println("C: " + get_R4());
	}

	public Boolean get_flag_F() {
		return flag_F;
	}

	public void set_flag_F(Boolean i) {
		flag_F = i;
	}

	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
