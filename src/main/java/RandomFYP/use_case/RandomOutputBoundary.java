package RandomFYP.use_case;

import RandomFYP.interface_adapter.RandomViewModel;

import java.io.IOException;

public interface RandomOutputBoundary {

    RandomViewModel setRandomViewModel(RandomOutput randomOutput) throws IOException;

}
