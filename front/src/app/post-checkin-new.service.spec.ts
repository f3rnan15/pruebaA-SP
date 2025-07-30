import { TestBed } from '@angular/core/testing';

import { PostCheckinNewService } from './checkin.service';

describe('PostCheckinNewService', () => {
  let service: PostCheckinNewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PostCheckinNewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
