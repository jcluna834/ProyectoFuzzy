//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//                                                      //
// Class:  TP_proyectoxFuzzy_cantidadpalabras                           //
//                                                      //
// Author: Automatically generated by Xfuzzy            //
//                                                      //
// Description: Type "cantidadpalabras"                      //
//                                                      //
//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

package CodigoFuzzy;

public class TP_proyectoxFuzzy_cantidadpalabras {
 private double min = 0.0;
 private double max = 20.0;
 private double step = 20.0;
 double _ps_bajo[] = { -2.0,0.0,4.0,8.0 };
 double _pl_bajo[] = {  };
 double _ps_medio[] = { 5.0,8.0,12.0,15.0 };
 double _pl_medio[] = {  };
 double _ps_alto[] = { 12.0,16.0,20.0,25.0 };
 double _pl_alto[] = {  };
 MF_xfl_trapezoid bajo = new MF_xfl_trapezoid(min,max,step,_ps_bajo,_pl_bajo);
 MF_xfl_trapezoid medio = new MF_xfl_trapezoid(min,max,step,_ps_medio,_pl_medio);
 MF_xfl_trapezoid alto = new MF_xfl_trapezoid(min,max,step,_ps_alto,_pl_alto);
}

