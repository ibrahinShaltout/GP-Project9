package com.example.ibrahimshaltout.uc_gp_1.accountactivity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ibrahimshaltout.uc_gp_1.MainActivity;
import com.example.ibrahimshaltout.uc_gp_1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IndividualInfoActivity1 extends AppCompatActivity {

    private static final int PICKFILE_RESULT_CODE = 1;
    MultiAutoCompleteTextView inputskills,inputinterest,inputExperience;
    private EditText inputSchool,inputSchoolType,inputUniversity,inputSpecialization,
            inputGrade,inputfieldof_diploma,
            inputfieldof_masters,inputfieldof_doctorate,
            inputcollege,inputstartYear,inputendYear,inputCompany,
            inputPosition,inputDep, inputCV;

    TextInputLayout inputSchoolLayout,inputSchoolTypeLayout,inputUniversityLayout,inputSpecializationLayout,
            inputGradeLayout,inputfieldof_diplomaLayout,inputfieldof_mastersLayout,inputfieldof_doctorateLayout,
            inputcollegeLayout,inputstartYearLayout,inputendYearLayout,inputCompanyLayout,inputPositionLayout,
            inputDepLayout,inputskillsLayout,inputinterestLayout,inputExperienceLayout;

    private Button btnNext,btnCv;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private int spinnerItemSelcected2;
    MaterialBetterSpinner materialDesignSpinner2;
    ArrayAdapter<String> arrayAdapter2;
    private StorageReference documentAttach;

    private String[] qualificationList = {"Preparatory School","High School", "Undergraduate", "Fresh graduate", "Diploma ", "Masters", "Doctorate ","Employee "};
    String[] skillsArray ={"Dwight D. Eisenhower","John F. Kennedy","Lyndon B. Johnson","Richard Nixon","Gerald Ford","Jimmy Carter",
                    "Ronald Reagan", "George H. W. Bush","Bill Clinton","George W. Bush","Barack Obama"};
    String[] interestsArray ={"Dwight D. Eisenhower","John F. Kennedy","Lyndon B. Johnson","Richard Nixon","Gerald Ford","Jimmy Carter",
            "Ronald Reagan", "George H. W. Bush","Bill Clinton","George W. Bush","Barack Obama"};
    String[] experienceArray ={"Dwight D. Eisenhower","John F. Kennedy","Lyndon B. Johnson","Richard Nixon","Gerald Ford","Jimmy Carter",
            "Ronald Reagan", "George H. W. Bush","Bill Clinton","George W. Bush","Barack Obama"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individualinfo_1);

                CountriesListClass CountriesDataList = new CountriesListClass();
//        ArrayList<String>CountriesList=cdl.xx();

//        Locale[] locales = Locale.getAvailableLocales();
//         countries = new ArrayList<String>();
//        for (Locale locale : locales) {
//            String country = locale.getDisplayCountry();
//            if (country.trim().length()>0 && !countries.contains(country)) {
//                countries.add(country);
//            }
//        }
//        Collections.sort(countries);
//        for (String country : countries) {
//           Toast.makeText(getApplicationContext(), country, Toast.LENGTH_SHORT).show();
//
//        }
        auth = FirebaseAuth.getInstance();
        btnNext = (Button) findViewById(R.id.individualNext1);
        inputSchool = (EditText) findViewById(R.id.School_Name);
        inputSchoolType = (EditText) findViewById(R.id.School_type_Name);
        inputUniversity = (EditText) findViewById(R.id.university_Name);
        inputSpecialization = (EditText) findViewById(R.id.Specialization_name);
        inputGrade = (EditText) findViewById(R.id.grade_name);
        inputfieldof_diploma = (EditText) findViewById(R.id.FieldDiploma);
        inputfieldof_masters = (EditText) findViewById(R.id.FieldMasters);
        inputfieldof_doctorate = (EditText) findViewById(R.id.FieldDoctorate);
        inputcollege = (EditText) findViewById(R.id.CollegeName);
        inputstartYear = (EditText) findViewById(R.id.start_Year);
        inputendYear = (EditText) findViewById(R.id.end_Year);
        inputCompany = (EditText) findViewById(R.id.Company_name);
        inputPosition = (EditText) findViewById(R.id.Job_Title);
        inputDep= (EditText) findViewById(R.id.Department);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        inputSchoolLayout = (TextInputLayout) findViewById(R.id.LayoutSchool_Name);
        inputSchoolTypeLayout = (TextInputLayout) findViewById(R.id.LayoutSchool_type_Name);
        inputUniversityLayout = (TextInputLayout) findViewById(R.id.Layoutuniversity_Name);
        inputSpecializationLayout = (TextInputLayout) findViewById(R.id.LayoutSpecialization_name);
        inputGradeLayout = (TextInputLayout) findViewById(R.id.Layoutgrade_name);
        inputfieldof_diplomaLayout = (TextInputLayout) findViewById(R.id.LayoutFieldDiploma);
        inputfieldof_mastersLayout = (TextInputLayout) findViewById(R.id.LayoutFieldMasters);
        inputfieldof_doctorateLayout = (TextInputLayout) findViewById(R.id.LayoutFieldDoctorate);
        inputcollegeLayout = (TextInputLayout) findViewById(R.id.LayoutCollegeName);
        inputstartYearLayout = (TextInputLayout) findViewById(R.id.Layoutstart_Year);
        inputendYearLayout = (TextInputLayout) findViewById(R.id.Layoutend_Year);
        inputCompanyLayout = (TextInputLayout) findViewById(R.id.LayoutCompany_name);
        inputPositionLayout = (TextInputLayout) findViewById(R.id.LayoutJob_Title);
        inputDepLayout= (TextInputLayout) findViewById(R.id.LayoutDepartment);
        inputskillsLayout= (TextInputLayout) findViewById(R.id.LayoutSkills_Talents);
        inputinterestLayout= (TextInputLayout) findViewById(R.id.LayoutInterests);
        inputExperienceLayout= (TextInputLayout) findViewById(R.id.Layoutexperience);















        ArrayAdapter<String> skillsadapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,skillsArray);
        inputskills = (MultiAutoCompleteTextView) findViewById(R.id.Skills_Talents);
        inputskills.setThreshold(1);
        inputskills.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        inputskills.setAdapter(skillsadapter);
        ArrayAdapter<String> experienceadapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,experienceArray);
        inputExperience = (MultiAutoCompleteTextView) findViewById(R.id.experience);
        inputExperience.setThreshold(1);
        inputExperience.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        inputExperience.setAdapter(experienceadapter);
        ArrayAdapter<String> Interestsadapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,interestsArray);
        inputinterest = (MultiAutoCompleteTextView) findViewById(R.id.Interests);
        inputinterest.setThreshold(1);
        inputinterest.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        inputinterest.setAdapter(Interestsadapter);


        inputSchoolLayout.setVisibility(View.GONE);
        inputSchoolTypeLayout.setVisibility(View.GONE);
        inputstartYearLayout.setVisibility(View.GONE);
        inputendYearLayout.setVisibility(View.GONE);
        inputinterestLayout.setVisibility(View.GONE);
        inputskillsLayout.setVisibility(View.GONE);
        inputExperienceLayout.setVisibility(View.GONE);
        inputinterestLayout.setVisibility(View.GONE);
        inputUniversityLayout.setVisibility(View.GONE);
        inputSpecializationLayout.setVisibility(View.GONE);
        inputGradeLayout.setVisibility(View.GONE);
        inputfieldof_diplomaLayout.setVisibility(View.GONE);
        inputfieldof_mastersLayout.setVisibility(View.GONE);
        inputfieldof_doctorateLayout.setVisibility(View.GONE);
        inputcollegeLayout.setVisibility(View.GONE);
        inputCompanyLayout.setVisibility(View.GONE);
        inputPositionLayout.setVisibility(View.GONE);
        inputDepLayout.setVisibility(View.GONE);

        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, qualificationList);
        materialDesignSpinner2 = (MaterialBetterSpinner) findViewById(R.id.Levelofqualification);
        materialDesignSpinner2.setAdapter(arrayAdapter2);
        materialDesignSpinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemSelcected2 = position;

                if (spinnerItemSelcected2==0){
                    inputSchoolLayout.setVisibility(View.VISIBLE);
                    inputSchoolTypeLayout.setVisibility(View.VISIBLE);
                    inputstartYearLayout.setVisibility(View.GONE);
                    inputendYearLayout.setVisibility(View.GONE);
                    inputinterestLayout.setVisibility(View.VISIBLE);
                    inputskillsLayout.setVisibility(View.VISIBLE);
                    inputExperienceLayout.setVisibility(View.GONE);
                    inputinterestLayout.setVisibility(View.VISIBLE);
                    inputUniversityLayout.setVisibility(View.GONE);
                    inputSpecializationLayout.setVisibility(View.GONE);
                    inputGradeLayout.setVisibility(View.GONE);
                    inputfieldof_diplomaLayout.setVisibility(View.GONE);
                    inputfieldof_mastersLayout.setVisibility(View.GONE);
                    inputfieldof_doctorateLayout.setVisibility(View.GONE);
                    inputcollegeLayout.setVisibility(View.GONE);
                    inputCompanyLayout.setVisibility(View.GONE);
                    inputPositionLayout.setVisibility(View.GONE);
                    inputDepLayout.setVisibility(View.GONE);
                        }
                else if (spinnerItemSelcected2==1)
                  {
                      inputSchoolLayout.setVisibility(View.VISIBLE);
                      inputSchoolTypeLayout.setVisibility(View.VISIBLE);
                      inputstartYearLayout.setVisibility(View.GONE);
                      inputendYearLayout.setVisibility(View.GONE);
                      inputinterestLayout.setVisibility(View.VISIBLE);
                      inputskillsLayout.setVisibility(View.VISIBLE);
                      inputExperienceLayout.setVisibility(View.GONE);
                      inputinterestLayout.setVisibility(View.VISIBLE);
                      inputUniversityLayout.setVisibility(View.GONE);
                      inputSpecializationLayout.setVisibility(View.GONE);
                      inputGradeLayout.setVisibility(View.GONE);
                      inputfieldof_diplomaLayout.setVisibility(View.GONE);
                      inputfieldof_mastersLayout.setVisibility(View.GONE);
                      inputfieldof_doctorateLayout.setVisibility(View.GONE);
                      inputcollegeLayout.setVisibility(View.GONE);
                      inputCompanyLayout.setVisibility(View.GONE);
                      inputPositionLayout.setVisibility(View.GONE);
                      inputDepLayout.setVisibility(View.GONE);
                  }
                else if (spinnerItemSelcected2==2)
                {
                    inputUniversityLayout.setVisibility(View.VISIBLE);
                    inputSpecializationLayout.setVisibility(View.VISIBLE);
                    inputcollegeLayout.setVisibility(View.VISIBLE);
                    inputstartYearLayout.setVisibility(View.GONE);
                    inputendYearLayout.setVisibility(View.GONE);
                    inputinterestLayout.setVisibility(View.VISIBLE);
                    inputskillsLayout.setVisibility(View.VISIBLE);
                    inputExperienceLayout.setVisibility(View.GONE);
                    inputSchoolLayout.setVisibility(View.GONE);
                    inputSchoolTypeLayout.setVisibility(View.GONE);
                    inputGradeLayout.setVisibility(View.GONE);
                    inputfieldof_diplomaLayout.setVisibility(View.GONE);
                    inputfieldof_mastersLayout.setVisibility(View.GONE);
                    inputfieldof_doctorateLayout.setVisibility(View.GONE);
                    inputCompanyLayout.setVisibility(View.GONE);
                    inputPositionLayout.setVisibility(View.GONE);
                    inputDepLayout.setVisibility(View.GONE);
                }
                else if (spinnerItemSelcected2==3)
                {
                    inputUniversityLayout.setVisibility(View.VISIBLE);
                    inputSpecializationLayout.setVisibility(View.VISIBLE);
                    inputGradeLayout.setVisibility(View.VISIBLE);
                    inputcollegeLayout.setVisibility(View.VISIBLE);
                    inputstartYearLayout.setVisibility(View.GONE);
                    inputendYearLayout.setVisibility(View.GONE);
                    inputinterestLayout.setVisibility(View.VISIBLE);
                    inputskillsLayout.setVisibility(View.VISIBLE);
                    inputExperienceLayout.setVisibility(View.GONE);
                    inputSchoolLayout.setVisibility(View.GONE);
                    inputSchoolTypeLayout.setVisibility(View.GONE);
                    inputfieldof_diplomaLayout.setVisibility(View.GONE);
                    inputfieldof_mastersLayout.setVisibility(View.GONE);
                    inputfieldof_doctorateLayout.setVisibility(View.GONE);
                    inputCompanyLayout.setVisibility(View.GONE);
                    inputPositionLayout.setVisibility(View.GONE);
                    inputDepLayout.setVisibility(View.GONE);
                }
                else if (spinnerItemSelcected2==4)
                {
                    inputSchoolLayout.setVisibility(View.GONE);
                    inputSchoolTypeLayout.setVisibility(View.GONE);
                    inputUniversityLayout.setVisibility(View.VISIBLE);
                    inputSpecializationLayout.setVisibility(View.VISIBLE);
                    inputGradeLayout.setVisibility(View.GONE);
                    inputcollegeLayout.setVisibility(View.VISIBLE);
                    inputfieldof_diplomaLayout.setVisibility(View.VISIBLE);
                    inputfieldof_mastersLayout.setVisibility(View.GONE);
                    inputfieldof_doctorateLayout.setVisibility(View.GONE);
                    inputstartYearLayout.setVisibility(View.GONE);
                    inputendYearLayout.setVisibility(View.GONE);
                    inputinterestLayout.setVisibility(View.VISIBLE);
                    inputExperienceLayout.setVisibility(View.VISIBLE);
                    inputskillsLayout.setVisibility(View.VISIBLE);
                    inputCompanyLayout.setVisibility(View.VISIBLE);
                    inputPositionLayout.setVisibility(View.VISIBLE);
                    inputDepLayout.setVisibility(View.VISIBLE);
                }
                else if (spinnerItemSelcected2==5)
                {
                    inputSchoolLayout.setVisibility(View.GONE);
                    inputSchoolTypeLayout.setVisibility(View.GONE);
                    inputUniversityLayout.setVisibility(View.VISIBLE);
                    inputSpecializationLayout.setVisibility(View.VISIBLE);
                    inputGradeLayout.setVisibility(View.GONE);
                    inputcollegeLayout.setVisibility(View.VISIBLE);
                    inputfieldof_diplomaLayout.setVisibility(View.GONE);
                    inputfieldof_mastersLayout.setVisibility(View.VISIBLE);
                    inputfieldof_doctorateLayout.setVisibility(View.GONE);
                    inputstartYearLayout.setVisibility(View.GONE);
                    inputendYearLayout.setVisibility(View.GONE);
                    inputinterestLayout.setVisibility(View.VISIBLE);
                    inputExperienceLayout.setVisibility(View.VISIBLE);
                    inputskillsLayout.setVisibility(View.VISIBLE);
                    inputCompanyLayout.setVisibility(View.VISIBLE);
                    inputPositionLayout.setVisibility(View.VISIBLE);
                    inputDepLayout.setVisibility(View.VISIBLE);
                }
                else if (spinnerItemSelcected2==6)
                {
                    inputSchoolLayout.setVisibility(View.GONE);
                    inputSchoolTypeLayout.setVisibility(View.GONE);
                    inputUniversityLayout.setVisibility(View.VISIBLE);
                    inputSpecializationLayout.setVisibility(View.VISIBLE);
                    inputGradeLayout.setVisibility(View.GONE);
                    inputcollegeLayout.setVisibility(View.VISIBLE);
                    inputfieldof_diplomaLayout.setVisibility(View.GONE);
                    inputfieldof_mastersLayout.setVisibility(View.GONE);
                    inputfieldof_doctorateLayout.setVisibility(View.VISIBLE);
                    inputstartYearLayout.setVisibility(View.GONE);
                    inputendYearLayout.setVisibility(View.GONE);
                    inputinterestLayout.setVisibility(View.VISIBLE);
                    inputExperienceLayout.setVisibility(View.VISIBLE);
                    inputskillsLayout.setVisibility(View.VISIBLE);
                    inputCompanyLayout.setVisibility(View.VISIBLE);
                    inputPositionLayout.setVisibility(View.VISIBLE);
                    inputDepLayout.setVisibility(View.VISIBLE);
                }
                else if (spinnerItemSelcected2==7)
                {
                    inputSchoolLayout.setVisibility(View.GONE);
                    inputSchoolTypeLayout.setVisibility(View.GONE);
                    inputUniversityLayout.setVisibility(View.VISIBLE);
                    inputSpecializationLayout.setVisibility(View.VISIBLE);
                    inputGradeLayout.setVisibility(View.GONE);
                    inputfieldof_diplomaLayout.setVisibility(View.GONE);
                    inputfieldof_mastersLayout.setVisibility(View.GONE);
                    inputfieldof_doctorateLayout.setVisibility(View.GONE);
                    inputcollegeLayout.setVisibility(View.VISIBLE);
                    inputstartYearLayout.setVisibility(View.GONE);
                    inputendYearLayout.setVisibility(View.GONE);
                    inputskillsLayout.setVisibility(View.VISIBLE);
                    inputExperienceLayout.setVisibility(View.VISIBLE);
                    inputinterestLayout.setVisibility(View.VISIBLE);
                    inputCompanyLayout.setVisibility(View.VISIBLE);
                    inputPositionLayout.setVisibility(View.VISIBLE);
                    inputDepLayout.setVisibility(View.VISIBLE);

                }            }
        });

//        btnCv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent,PICKFILE_RESULT_CODE);
//
//            }
//        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String listSkillString = inputskills.getText().toString();
                final String SkillstrArray[] = listSkillString.split(",");
                List listSkill=new ArrayList<String>(Arrays.asList(SkillstrArray));

                String listInterestsString = inputinterest.getText().toString();
                final String InterestsstrArray[] = listInterestsString.split(",");
                List listInterests=new ArrayList<String>(Arrays.asList(InterestsstrArray));

                String listExperienceString = inputExperience.getText().toString();
                final String experiencestrArray[] = listExperienceString.split(",");
                List listExperience=new ArrayList<String>(Arrays.asList(experiencestrArray));
//                String listInterestsString = inputinterest.getText().toString();
//                final String locales[] = listInterestsString.split(",");
//                List listInterests=new ArrayList<String>(Arrays.asList(locales));
                //   final String skills = inputskills.getText().toString();
                //   final String intresets = inputinterest.getText().toString().trim();
                final String schoolName = inputSchool.getText().toString().trim();
                final String SchoolType = inputSchoolType.getText().toString().trim();
                final String universityName = inputUniversity.getText().toString().trim();
                final String specialization = inputSpecialization.getText().toString().trim();
                final String grade = inputGrade.getText().toString().trim();
                final String field_Of_diploma = inputfieldof_diploma.getText().toString().trim();
                final String field_Of_masters = inputfieldof_masters.getText().toString().trim();
                final String field_Of_doctorate = inputfieldof_doctorate.getText().toString().trim();
                final String collegeName = inputcollege.getText().toString().trim();
                final String startYear = inputstartYear.getText().toString().trim();
                final String endYear = inputendYear.getText().toString().trim();
                final String companyName = inputCompany.getText().toString().trim();
                final String position = inputPosition.getText().toString().trim();
                final String Dep = inputDep.getText().toString().trim();

                 String individualId=auth.getUid();
                Toast.makeText(getApplicationContext(), individualId, Toast.LENGTH_SHORT).show();
//                if (TextUtils.isEmpty(schoolname)) {
//                    Toast.makeText(getApplicationContext(), "Enter School Name!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(collegename)) {
//                    Toast.makeText(getApplicationContext(), "Enter College Name!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(level)) {
//                    Toast.makeText(getApplicationContext(), "Enter your level!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (TextUtils.isEmpty(skills)) {
//                    Toast.makeText(getApplicationContext(), "Enter skills!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(intresets)) {
//                    Toast.makeText(getApplicationContext(), "Enter intresets!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(company)) {
//                    Toast.makeText(getApplicationContext(), "Enter Company Name!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(position)) {
//                    Toast.makeText(getApplicationContext(), "Enter position!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(Dep)) {
//                    Toast.makeText(getApplicationContext(), "Enter Department!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(CV)) {
//                    Toast.makeText(getApplicationContext(), "attach your CV!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                progressBar.setVisibility(View.VISIBLE);
                IndividualDataClass individualDataClass = new IndividualDataClass();
                String theLevelOfQualification= signUpAs();
                individualDataClass.setQualificationLevel(theLevelOfQualification);
                individualDataClass.setInputSchool(schoolName);
                individualDataClass.setInputSchoolType(SchoolType);
                individualDataClass.setInputUniversity(universityName);
                individualDataClass.setInputSpecialization(specialization);
                individualDataClass.setInputGrade(grade);
                individualDataClass.setFieldof_diploma(field_Of_diploma);
                individualDataClass.setFieldof_masters(field_Of_masters);
                individualDataClass.setFieldof_doctorate(field_Of_doctorate);
                individualDataClass.setInputcollege(collegeName);
                individualDataClass.setStartYearDate(startYear);
                individualDataClass.setEndYearDate(endYear);
                individualDataClass.setInputCompany(companyName);
                individualDataClass.setInputPosition(position);
                individualDataClass.setInputDep(Dep);
                individualDataClass.setInputinterest(listInterests);
                individualDataClass.setInputskills(listSkill);
                individualDataClass.setExperience(listExperience);




                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("qualificationLevel").setValue(individualDataClass.qualificationLevel);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputSchool").setValue(individualDataClass.inputSchool);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputSchoolType").setValue(individualDataClass.inputSchoolType);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputUniversity").setValue(individualDataClass.inputUniversity);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputcollege").setValue(individualDataClass.inputcollege);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputSpecialization").setValue(individualDataClass.inputSpecialization);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputGrade").setValue(individualDataClass.inputGrade);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("fieldof_diploma").setValue(individualDataClass.fieldof_diploma);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("fieldof_masters").setValue(individualDataClass.fieldof_masters);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("fieldof_doctorate").setValue(individualDataClass.fieldof_doctorate);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("startYearDate").setValue(individualDataClass.startYearDate);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("endYearDate").setValue(individualDataClass.endYearDate);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputskills").setValue(individualDataClass.inputskills);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputinterest").setValue(individualDataClass.inputinterest);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("experience").setValue(individualDataClass.experience);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputCompany").setValue(individualDataClass.inputCompany);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputPosition").setValue(individualDataClass.inputPosition);
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputDep").setValue(individualDataClass.inputDep);
//                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputCV").setValue(individualDataClass.inputCV);
//                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("inputlevel").setValue(individualDataClass.inputlevel);



                Intent intent = new Intent (IndividualInfoActivity1.this, MainActivity.class );
                startActivity(intent);

            }});
    }
//
//@Override
//protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    // TODO Auto-generated method stub
//    IndividualDataClass individualDataClass = new IndividualDataClass();
//
//    if (requestCode == PICKFILE_RESULT_CODE) {
//        Uri filePath=data.getData();
//        final StorageReference photoRef = documentAttach.child(filePath.getLastPathSegment());
//        photoRef.putFile(filePath);
//        FirebaseDatabase.getInstance().getReference("Users").child(auth.getUid()).child("inputCV").setValue(individualDataClass.inputCV);
//
//
//
//
//    }}

    private String signUpAs() {
        String theLevel="null";
    for (int i=0;i<qualificationList.length;i++)
                 {
                     if (spinnerItemSelcected2 == i) {
                         theLevel=qualificationList[i];
                     }
                 }
                 return(theLevel);
    }

}


