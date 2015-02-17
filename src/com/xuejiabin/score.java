package com.xuejiabin;

import android.R.integer;
import android.app.Activity;

import com.imudges.keepmoving.R;
import com.neo.database.Person;
public class score {

	/**
	 * @param args
	 */
	Person person;
	int i;
	public void increase(Activity activity)
	{
		person=new Person();
		person.Connect(activity);
		person.GetUser();
		if(person.GetUser()==0)
		{
		    i=java.lang.Integer.parseInt(person.Achievement, 10);
			i++;
			person.Achievement=Integer.toString(i);
			headimage();
			person.Update();
		}
	}
	public void reduce(Activity activity)
	{
		person=new Person();
		person.Connect(activity);
		person.GetUser();
		if(person.GetUser()==0)
		{
		    i=java.lang.Integer.parseInt(person.Achievement, 10);
		    if(i>0)
		    {
			i--;
		    }
			person.Achievement=Integer.toString(i);
			headimage();
			person.Update();
		}
	}
	private void headimage()
	{
		if(i<5)
	    {
			person.UserImage=Integer.toString(R.drawable.b);
	    }
	    else
	    {
	    	if(i<15)
	    	{
	    		person.UserImage=Integer.toString(R.drawable.b);
	    	}
	    	else
	    	{
	    		if(i<35)
	    		{
	    			person.UserImage=Integer.toString(R.drawable.b);
	    		}
	    		else
	    		{
	    			if(i<55)
	    			{
	    				person.UserImage=Integer.toString(R.drawable.b);
	    			}
	    			else
	    			{
	    				if(i<90)
	    				{
	    					person.UserImage=Integer.toString(R.drawable.b);
	    				}
	    				else
	    				{
	    					if(i<160)
	    					{
	    						person.UserImage=Integer.toString(R.drawable.b);
	    					}
	    					else
	    					{
	    						if(i<260)
	    						{
	    							person.UserImage=Integer.toString(R.drawable.b);
	    						}
	    						else
	    						{
	    							if(i<400)
	    							{
	    								person.UserImage=Integer.toString(R.drawable.b);
	    							}
	    							else
	    							{
	    								if(i<550)
	    								{
	    									person.UserImage=Integer.toString(R.drawable.b);
	    								}
	    								else
	    								{
	    									if(i<700)
	    									{
	    										person.UserImage=Integer.toString(R.drawable.b);
	    									}
	    									else
	    									{
	    										if(i<880)
	    										{
	    											person.UserImage=Integer.toString(R.drawable.b);
	    										}
	    										else
	    										{
	    											if(i<1000)
	    											{
	    												person.UserImage=Integer.toString(R.drawable.b);
	    											}
	    											else
	    											{
	    												if(i<2000)
	    												{
	    													person.UserImage=Integer.toString(R.drawable.b);
	    												}
	    												else
	    												{
	    													if(i<5000)
	    													{
	    														person.UserImage=Integer.toString(R.drawable.b);
	    													}
	    													else
	    													{
	    														person.UserImage=Integer.toString(R.drawable.b);
	    													}
	    												}
	    											}
	    										}
	    									}
	    								}
	    							}
	    						}
	    					}
	    				}
	    			}
	    			}
	    		}
	    }
	}
}
