package com.julyyu.arsenal.exercise.serializationExercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.julyyu.arsenal.R;
import com.julyyu.uilibrary.fragment.BaseFragment;
import com.julyyu.utilslibrary.fileProcessing.FilePathUtils;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by julyyu on 2018/1/30.
 */

public class SerializationTestFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_serialization;
    }

    String path = FilePathUtils.getEnvironmentPath();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = new Bundle();
        try {
            Person ted = new Person("Ted", "Neward", 39);
            Person charl = new Person("Charlotte",
                    "Neward", 38);
            Drivder drivder = new Drivder("July","秋名山","AA86");
            DrivderParcelable drivderParcelable = new DrivderParcelable("July","秋名山","AA86");
            BusDrivder busDrivder = new BusDrivder(drivder,2222);
            CarDrivder carDrivder = new CarDrivder(drivderParcelable,333);
            //            ted.setSpouse(charl); charl.setSpouse(ted);
            /**
             * 读写操作
             */
//            FileOutputStream   fos = new FileOutputStream(path+ "/tempdata.txt");
//            fos.write(new byte[]{'x','x','s','e','k'});
//            fos.close();
//            FileInputStream fio = new FileInputStream(path+ "/tempdata.txt");
//            byte[] value = new byte[1];
//            while (fio.read(value) != -1){
//                Log.i("TAG", "onViewCreated: " + Byte.toString(value[value.length - 1]));
//            }
//            ByteArrayInputStream
            /**
             * 对象序列化
             */
            FileOutputStream   fos = new FileOutputStream(path + "/tempdata.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(ted);
//            oos.writeObject(charl);
//            oos.writeObject(drivder);
//            oos.writeObject(drivderParcelable);
            oos.close();


            bundle.putParcelable("bus",busDrivder);
            bundle.putSerializable("car",carDrivder);

//            Log.i("TAG", "onViewCreated: " + ted.getFirstName()+ "---" + ted.getSpouse().getFirstName());
        } catch (Exception ex) {

            Log.i("TAG", "ObjectOutputStream: " + ex.toString());
        } finally {

        }

        try {
//            FileInputStream   fis = new FileInputStream(path + "/tempdata.txt");
//            ObjectInputStream ois = new ObjectInputStream(fis);
////            Person            ted = (Person) ois.readObject();
////            Log.i("TAG", "ObjectInputStream: " + ted.getFirstName() + "--" + ted.getLastName() + "--" + ted.getAge());
//            byte[] value = new byte[1];
//            while (ois.read(value) != -1) {
//                Log.i("TAG", "ObjectInputStream: " + Byte.toString(value[value.length - 1]));
//            }
//            ois.close();
            BusDrivder busDrivder = bundle.getParcelable("bus");
            CarDrivder carDrivder = (CarDrivder) bundle.getSerializable("car");
            Log.i("TAG", "BusDrivder: " + busDrivder.toString());
            Log.i("TAG", "CarDrivder: " + carDrivder.drivderParcelable.toString());
//            Log.i("TAG", "ObjectInputStream: " + Byte.toString(value[value.length - 1]));
//            assertEquals(ted.getFirstName(, "Ted");
//            assertEquals(ted.getSpouse().getFirstName(,"Charlotte");

            // Clean up the file
//            new File(path +"tempdata.txt").delete();
        } catch (Exception ex) {
            Log.i("TAG", "ObjectInputStream: " + ex.toString());
//            fail("Exception thrown during test: " + ex.toString());
        }
    }


}
